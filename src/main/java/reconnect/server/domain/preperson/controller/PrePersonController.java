package reconnect.server.domain.preperson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reconnect.server.domain.preperson.model.request.PrePersonRequest;
import reconnect.server.domain.preperson.model.response.PrePersonResponse;
import reconnect.server.domain.preperson.service.PrePersonService;
import reconnect.server.global.model.entity.mysql.PrePerson;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/prepersons")
public class PrePersonController {

    private final PrePersonService prePersonService;

    @Autowired
    public PrePersonController(PrePersonService prePersonService) {
        this.prePersonService = prePersonService;
    }

    // 사전 등록 실종자 목록 조회
    @GetMapping
    public List<PrePersonResponse> getPrePersons() {
        return prePersonService.getPrePersonResponses();
    }

    // 사전 등록 실종자 등록 (최종 저장)
    @PostMapping
    public PrePerson createPrePerson(@RequestBody PrePersonRequest prePersonRequest) {
        return prePersonService.createPrePerson(prePersonRequest);
    }
}
