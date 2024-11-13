package reconnect.server.domain.missing_person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reconnect.server.domain.missing_person.model.response.MissingPersonResponse;
import reconnect.server.domain.missing_person.service.MissingPersonService;

import java.util.List;

@RestController
@RequestMapping("/api/missing-persons")
public class MissingPersonController {

    private final MissingPersonService missingPersonService;

    @Autowired
    public MissingPersonController(MissingPersonService missingPersonService) {
        this.missingPersonService = missingPersonService;
    }

    // 실종자 목록 조회
    @GetMapping
    public List<MissingPersonResponse> getMissingPersons(@RequestParam(defaultValue = "REGISTRATION_DATE") String sortBy) {
        return missingPersonService.getMissingPersons(sortBy);
    }

    // 실종자 상세 조회
    @GetMapping("/{id}")
    public MissingPersonResponse getMissingPersonDetails(@PathVariable Long id) {
        return missingPersonService.getMissingPersonDetails(id);
    }
}
