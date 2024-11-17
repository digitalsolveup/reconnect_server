package reconnect.server.domain.preperson.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "PrePerson", description = "실종자 사전 등록 관련 컨트롤러")
public class PrePersonController {

    private final PrePersonService prePersonService;

    @Autowired
    public PrePersonController(PrePersonService prePersonService) {
        this.prePersonService = prePersonService;
    }

    // 사전 등록 실종자 목록 조회
    @Operation(summary = "사전 등록 실종자 목록 조회", description = """
            사전 등록 실종자 목록을 조회한다.
            """)
    @GetMapping
    public List<PrePersonListResponse> getPrePersons() {
        return prePersonService.getPrePersonResponses();
    }

    // 사전 등록 실종자 등록 (최종 저장)
    @Operation(summary = "사전 등록 실종자 추가", description = """
            사전 등록 실종자를 추가한다.
            """)
    @PostMapping
    public PrePersonListResponse createPrePerson(@RequestBody PrePersonRequest prePersonRequest) {
        return prePersonService.createPrePerson(prePersonRequest);
    }

    // 사전 등록 실종자 수정
    @Operation(summary = "사전 등록 실종자 수정", description = """
            사전 등록 실종자를 수정한다.
            """)
    @PutMapping("/{id}")
    public PrePersonDetailResponse updatePrePerson(@PathVariable("id") Long id, @RequestBody UpdatePrePersonRequest updateRequest) {
        return prePersonService.updatePrePerson(id, updateRequest);
    }

    // 사전 등록 실종자 삭제
    @Operation(summary = "사전 등록 실종자 삭제", description = """
            사전 등록 실종자를 삭제한다.
            """)
    @DeleteMapping("/{id}")
    public void deletePrePerson(@PathVariable("id") Long id) {
        prePersonService.deletePrePerson(id);
    }


}
