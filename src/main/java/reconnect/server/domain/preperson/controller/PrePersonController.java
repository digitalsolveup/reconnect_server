package reconnect.server.domain.preperson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reconnect.server.domain.preperson.model.request.PrePersonRequest;
import reconnect.server.domain.preperson.model.request.UpdatePrePersonRequest;
import reconnect.server.domain.preperson.model.response.PrePersonDetailResponse;
import reconnect.server.domain.preperson.model.response.PrePersonListResponse;
import reconnect.server.domain.preperson.service.PrePersonService;
import reconnect.server.global.model.entity.mysql.PrePerson;

import java.util.List;

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
    public List<PrePersonListResponse> getPrePersons() {
        return prePersonService.getPrePersonResponses();
    }

    // 사전 등록 실종자 등록 (최종 저장)
    @PostMapping
    public PrePersonListResponse createPrePerson(@RequestBody PrePersonRequest prePersonRequest) {
        return prePersonService.createPrePerson(prePersonRequest);
    }

    // 사전 등록 실종자 수정
    @PutMapping("/{id}")
    public PrePersonDetailResponse updatePrePerson(@PathVariable("id") Long id, @RequestBody UpdatePrePersonRequest updateRequest) {
        return prePersonService.updatePrePerson(id, updateRequest);
    }

    // 사전 등록 실종자 삭제
    @DeleteMapping("/{id}")
    public void deletePrePerson(@PathVariable("id") Long id) {
        prePersonService.deletePrePerson(id);
    }


}
