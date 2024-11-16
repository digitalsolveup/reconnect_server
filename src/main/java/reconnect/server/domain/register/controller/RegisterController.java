package reconnect.server.domain.register.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reconnect.server.domain.register.service.RegisterService;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/register")
@Tag(name = "Register", description = "실종위험자 실종 신고 관련 컨트롤러")
/**
 * 실종위험자 실종 신고 관련 Controller
 */
public class RegisterController {

    private final RegisterService registerService;


}
