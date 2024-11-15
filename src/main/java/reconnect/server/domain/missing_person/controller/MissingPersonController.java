package reconnect.server.domain.missing_person.controller;

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
public class MissingPersonController {

    private final MissingPersonService missingPersonService;

    @Autowired
    public MissingPersonController(MissingPersonService missingPersonService) {
        this.missingPersonService = missingPersonService;
    }

    // 실종자 목록 조회
    @GetMapping
    public List<MissingPersonListResponse> getMissingPersons(
            @RequestParam(value = "sortBy", defaultValue = "DISTANCE") String sortBy,
            @RequestParam(value = "currentLatitude") double currentLatitude,
            @RequestParam(value = "currentLongitude") double currentLongitude
    ) {
        return missingPersonService.getMissingPersons(sortBy, currentLatitude, currentLongitude);
    }


    // 실종자 상세 정보 조회
    @GetMapping("/{id}")
    public MissingPersonDetailResponse getMissingPersonDetails(@PathVariable("id") Long id) {
        return missingPersonService.getMissingPersonDetails(id);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace(); // 로그에 자세한 정보 출력
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }

}
