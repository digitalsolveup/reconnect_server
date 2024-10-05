package reconnect.server.alarm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reconnect.server.alarm.mapper.AlarmInfoMapper;
import reconnect.server.alarm.model.request.AlarmRequest;
import reconnect.server.alarm.model.request.AlarmUrlResponse;
import reconnect.server.alarm.model.response.AlarmResponse;
import reconnect.server.alarm.model.vo.UserAlarmInfo;
import reconnect.server.alarm.repository.AlarmRepository;
import reconnect.server.alarm.repository.EmitterRepository;
import reconnect.server.auth.security.LoginManager;
import reconnect.server.global.model.entity.mongo.user.USER_ALARM;
import reconnect.server.global.model.response.Response;
import reconnect.server.global.util.DateUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static reconnect.server.global.constants.ErrorCode.RESOURCE_NOT_FOUND;
import static reconnect.server.global.util.DateUtil.DEFAULT_SSE_TIMEOUT;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmService {

    private final EmitterRepository emitterRepository;
    private final AlarmRepository alarmRepository;
    private final AlarmInfoMapper alarmInfoMapper;

    /**
     * SSE
     */

    public SseEmitter subscribe(String lastEventId) {
        Long userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserSeq();

        String emitterId = userId + "_" + System.currentTimeMillis();

        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_SSE_TIMEOUT));
        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));
        emitter.onError((e) -> emitterRepository.deleteById(emitterId));

        this.sendAlarm(emitter, emitterId,
                emitterId, "EventStream Created. [userId=%s]".formatted(userId));

        if (!lastEventId.isEmpty()) {
            Map<String, SseEmitter> events = emitterRepository.findAllStartByUserId(String.valueOf(userId));
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendAlarm(emitter, emitterId, entry.getKey(), entry.getValue()));
        }

        return emitter;
    }

    public Response sendAlarm(AlarmRequest request) {

        Long userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserSeq();
        String eventId = userId + "_" + System.currentTimeMillis(); // 데이터 유실 시점 파악 위함

        UserAlarmInfo alarmInfo = alarmInfoMapper.toEntity(request);
        createAlarm(userId, alarmInfo);

        // 유저의 모든 SseEmitter 가져옴
        Map<String, SseEmitter> emitters = emitterRepository.findAllStartByUserId(userId + "_");
        emitters.forEach(
                (key, emitter) -> {
                    emitterRepository.saveEventCache(key, alarmInfo);
                    this.sendAlarm(emitter, eventId, key, alarmInfo);
                }
        );

        return Response.success();
    }

    private void sendAlarm(SseEmitter emitter, String eventId, String emitterId, Object data){
        try {
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .name("message")
                    .data(data));
            log.info("SSE connection successful.");
        } catch (IOException exception) {
            emitterRepository.deleteById(emitterId);
            log.error("SSE connection failed. target : {}", eventId);
        }
    }

    private void createAlarm(Long userId, UserAlarmInfo alarmInfo){
        alarmInfo.setDefaultStatus();
        USER_ALARM alarm = alarmRepository.getRecentAlarmByUserId(userId);

        long txTime = DateUtil.dateToEpochMilli(LocalDateTime.now());

        if(alarm == null) {
            alarmInfo.set_id(txTime);
            alarm = USER_ALARM.builder()
                    ._id(userId + "_" + txTime + "_" + "ALARM")
                    .userId(userId)
                    .createTime(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1))
                    .alarmInfos(new ArrayList<>(List.of(alarmInfo)))
                    .build();
        } else {
            alarmInfo.set_id(txTime + alarm.getAlarmInfos().size());
            alarm.getAlarmInfos().add(alarmInfo);
        }

        alarmRepository.save(alarm);
    }

    /**
     * Alarm
     */
    public Response getAlarmList(){
        Long userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserSeq();
        List<USER_ALARM> userRecentAlarms = alarmRepository.getUserAlarmFromRecentThreeMonthsAgo(userId);

        return this.userAlarmInfos(userRecentAlarms);
    }

    public Response deleteAlarm(Long alarmId){
        Long userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserSeq();
        USER_ALARM userAlarm = alarmRepository.getUserAlarmByUserIdAndAlarmId(userId, alarmId);

        if(userAlarm == null)
            return Response.error(RESOURCE_NOT_FOUND);

        for(UserAlarmInfo userAlarmInfo : userAlarm.getAlarmInfos()){
            if(userAlarmInfo.get_id() == alarmId)
                userAlarmInfo.setIsDeleted(true);
        }

        alarmRepository.save(userAlarm);
        return Response.success();
    }

    public Response readAlarm(Long alarmId){
        Long userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserSeq();
        USER_ALARM userAlarm = alarmRepository.getUserAlarmByUserIdAndAlarmId(userId, alarmId);

        if(userAlarm == null)
            return Response.error(RESOURCE_NOT_FOUND);

        AlarmUrlResponse response = new AlarmUrlResponse();
        for(UserAlarmInfo userAlarmInfo : userAlarm.getAlarmInfos()){
            if(userAlarmInfo.get_id() == alarmId) {
                userAlarmInfo.setIsRead(true);
                response = AlarmUrlResponse.builder()
                        .redirectUrl(userAlarmInfo.getRedirectUrl())
                        .build();
            }
        }

        alarmRepository.save(userAlarm);

        return response;
    }

    public Response deleteAllReadAlarm(){
        Long userId = Objects.requireNonNull(LoginManager.getUserDetails()).getUserSeq();
        List<USER_ALARM> userRecentAlarms = alarmRepository.getUserAlarmFromRecentThreeMonthsAgo(userId);

        userRecentAlarms.forEach(userAlarm -> userAlarm.getAlarmInfos().stream().filter(UserAlarmInfo::getIsRead).forEach(alarmInfo -> alarmInfo.setIsDeleted(true)));

        userRecentAlarms = userRecentAlarms.stream().map(alarmRepository::save).collect(Collectors.toList()); // saveAll
        return this.userAlarmInfos(userRecentAlarms);
    }

    private AlarmResponse userAlarmInfos(List<USER_ALARM> userRecentAlarms){
        List<UserAlarmInfo> userAlarmInfos = new ArrayList<>();
        for(USER_ALARM recentAlarm : userRecentAlarms){
            userAlarmInfos.addAll(recentAlarm.getAlarmInfos());
        }
        return AlarmResponse.builder()
                .userAlarmInfos(userAlarmInfos)
                .build();
    }

}
