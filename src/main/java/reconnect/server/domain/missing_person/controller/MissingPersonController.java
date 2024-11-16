package reconnect.server.domain.missing_person.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reconnect.server.domain.missing_person.model.response.MissingPersonDetailResponse;
import reconnect.server.domain.missing_person.model.response.MissingPersonListResponse;
import reconnect.server.domain.missing_person.service.MissingPersonService;

import java.util.List;

@RestController
@RequestMapping("/api/missing_person")
@Tag(name = "MissingPerson", description = "실종자 조회 관련 컨트롤러")
public class MissingPersonController {

    private final MissingPersonService missingPersonService;

    @Autowired
    public MissingPersonController(MissingPersonService missingPersonService) {
        this.missingPersonService = missingPersonService;
    }

    // 실종자 목록 조회
    @Operation(summary = "실종자 목록 조회", description = """
            실종자 목록을 조회한다.
            - sortBy: 거리순(DISTANCE), 등록순(REGISTRATION_DATE), 제보 적은순(REPORT_COUNT)
            - 현재 위도, 경도값 입력
            """)
    @GetMapping
    public List<MissingPersonListResponse> getMissingPersons(
            @RequestParam(value = "sortBy", defaultValue = "DISTANCE") String sortBy,
            @RequestParam(value = "currentLatitude") double currentLatitude,
            @RequestParam(value = "currentLongitude") double currentLongitude
    ) {
        return missingPersonService.getMissingPersons(sortBy, currentLatitude, currentLongitude);
    }


    // 실종자 상세 정보 조회
    @Operation(summary = "실종자 상세 정보 조회", description = """
            실종자 상세 정보를 조회한다.
            - 실종자 고유 id 입력 1,2,3,...
            """)
    @GetMapping("/{id}")
    public MissingPersonDetailResponse getMissingPersonDetails(@PathVariable("id") Long id) {
        return missingPersonService.getMissingPersonDetails(id);
    }
}
