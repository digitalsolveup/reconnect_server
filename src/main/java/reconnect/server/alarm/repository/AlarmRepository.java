package reconnect.server.alarm.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reconnect.server.global.constants.MongoField;
import reconnect.server.global.model.entity.mongo.user.USER_ALARM;
import reconnect.server.global.repository.user.UserMongoRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class AlarmRepository extends UserMongoRepository<USER_ALARM> {
    public List<USER_ALARM> getUserAlarmFromRecentThreeMonthsAgo(Long userId){
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);

        Query query = new Query();
        query.addCriteria(Criteria.where(MongoField.USER_ID).is(userId));
        query.addCriteria(Criteria.where(MongoField.IS_DELETED_ALARM).is(false));
        query.addCriteria(Criteria.where(MongoField.CREATE_TIME).gte(threeMonthsAgo));

        return super.find(query);
    }

    public USER_ALARM getUserAlarmByUserIdAndAlarmId(Long userId, Long alarmId){
        Query query = new Query();
        query.addCriteria(Criteria.where(MongoField.USER_ID).is(userId));
        query.addCriteria(Criteria.where(MongoField.ALARM_ID).is(alarmId));
        query.with(Sort.by(Sort.Direction.ASC, MongoField.CREATE_TIME));

        return super.findOne(query);
    }

    public USER_ALARM getRecentAlarmByUserId(Long userId){
        Query query = new Query();
        query.addCriteria(Criteria.where(MongoField.USER_ID).is(userId));
        query.with(Sort.by(Sort.Direction.ASC, MongoField.CREATE_TIME));

        return super.findOne(query);
    }

    @Override
    protected Class<USER_ALARM> getDataType() {
        return USER_ALARM.class;
    }
}