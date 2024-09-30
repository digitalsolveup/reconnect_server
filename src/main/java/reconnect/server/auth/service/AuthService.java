package reconnect.server.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reconnect.server.auth.model.request.LoginRequest;
import reconnect.server.auth.model.response.SocialOAuthResponse;
import reconnect.server.auth.model.response.TokenResponse;
import reconnect.server.auth.repository.UserInfoRepository;
import reconnect.server.auth.repository.UserTokenRepository;
import reconnect.server.auth.security.JwtTokenProvider;
import reconnect.server.global.model.entity.UserInfo;
import reconnect.server.global.model.entity.UserToken;
import reconnect.server.global.model.response.Response;

import java.util.concurrent.atomic.AtomicReference;

import static reconnect.server.global.constants.AuthConstants.*;
import static reconnect.server.global.constants.ErrorCode.ERR_UNKNOWN;
import static reconnect.server.global.constants.ErrorCode.RESOURCE_NOT_FOUND;
import static reconnect.server.global.util.DateUtil.ONE_MONTH_IN_MILLISECONDS;
import static reconnect.server.global.constants.CommonConstants.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserInfoRepository userInfoRepository;
    private final UserTokenRepository userTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse login(HttpServletResponse response, LoginRequest request){
        UserInfo userInfo = userInfoRepository.findUserByEmail(request.getEmail());
        return this.makeToken(response, userInfo);
    }

    public Response doVerification(HttpServletResponse response, String provider, String accessToken){

        String baseUrl;
        switch (provider) {
            case GOOGLE -> baseUrl = GOOGLE_VALIDATE_URL;
            case NAVER -> baseUrl = NAVER_VALIDATE_URL;
            default -> throw new RuntimeException();
        }

        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        AtomicReference<Response> errorResponse = new AtomicReference<>(new Response());
        SocialOAuthResponse oAuthResponse =
                webClient
                        .get()
                        .header(AUTHORIZATION, BEARER + accessToken)
                        .retrieve()
                        .bodyToMono(SocialOAuthResponse.class)
                        .onErrorResume(error -> {
                            if (error instanceof WebClientResponseException ex) {
                                errorResponse.set(Response.error(ex.getStatusCode().is4xxClientError() ? RESOURCE_NOT_FOUND : ERR_UNKNOWN));
                            }
                            return Mono.empty();
                        })
                        .block();

        if(!errorResponse.get().isSuccess())
            return errorResponse.get();

        UserInfo userInfo;
        assert oAuthResponse != null;
        switch (provider) {
            case GOOGLE -> userInfo = userInfoRepository.findUserByEmail(oAuthResponse.getEmail());
            case NAVER -> {
                assert oAuthResponse.getResponse() != null;
                userInfo = userInfoRepository.findUserByEmail(oAuthResponse.getResponse().getEmail());
            }
            default -> throw new RuntimeException();
        }

        if (userInfo == null)
            return Response.error(RESOURCE_NOT_FOUND);

        return this.makeToken(response, userInfo);
    }

    private TokenResponse makeToken(HttpServletResponse response, UserInfo userInfo){
        Claims claims = Jwts.claims().setSubject(userInfo.getEmail());
        claims.put(NAME, userInfo.getName());

        String accessToken = jwtTokenProvider.createAccessToken(claims);
        String refreshToken = jwtTokenProvider.createRefreshToken(claims);

        UserToken userToken = UserToken.builder()
                .userSeq(userInfo.getUserSeq())
                .token(accessToken)
                .build();

        userTokenRepository.save(userToken);

        Cookie cookie = new Cookie(REFRESH_TOKEN_STR, refreshToken);
        cookie.setMaxAge((int) ONE_MONTH_IN_MILLISECONDS);
        response.addCookie(cookie);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }

}
