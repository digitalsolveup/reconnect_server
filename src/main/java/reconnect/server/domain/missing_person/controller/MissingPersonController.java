package reconnect.server.domain.missing_person.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reconnect.server.global.model.entity.mysql.MissingPerson;
import reconnect.server.domain.missing_person.service.MissingPersonService;

import java.util.List;

@RestController
@RequestMapping("/api/missing-persons")
@RequiredArgsConstructor
public class MissingPersonController {
    private final MissingPersonService missingPersonService;

    @GetMapping
    public List<MissingPerson> getAllMissingPersons() {
        return missingPersonService.getAllMissingPersons();
    }
}