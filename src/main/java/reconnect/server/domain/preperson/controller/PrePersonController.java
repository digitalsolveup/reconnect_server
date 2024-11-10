package reconnect.server.domain.preperson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reconnect.server.domain.preperson.model.entity.PrePerson;
import reconnect.server.domain.preperson.service.PrePersonService;

import java.util.List;

@RestController
@RequestMapping("/api/preperson")
@RequiredArgsConstructor
public class PrePersonController {

    private final PrePersonService prePersonService;

    // 모든 사전등록된 대상 조회
    @GetMapping
    public ResponseEntity<List<PrePerson>> getAllPrePersons() {
        List<PrePerson> prePersons = prePersonService.getAllPrePersons();
        return new ResponseEntity<>(prePersons, HttpStatus.OK);
    }

    // 새로운 대상 등록
    @PostMapping("/register")
    public ResponseEntity<PrePerson> registerPrePerson(@RequestBody PrePerson prePerson) {
        PrePerson savedPrePerson = prePersonService.registerPrePerson(prePerson);
        return new ResponseEntity<>(savedPrePerson, HttpStatus.CREATED);
    }
}