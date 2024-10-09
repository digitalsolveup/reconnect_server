package reconnect.server.domain.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reconnect.server.domain.auth.service.AuthDelegate;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import static reconnect.server.global.constants.CommonConstants.EMAIL;
import static reconnect.server.global.util.DateUtil.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;
    private Key key;
    private final AuthDelegate authDelegate;

    /**
     * 키 생성
     */
    @PostConstruct
    private void initKey() {
        byte[] decodedKey = Base64.getEncoder().encode(secretKey.getBytes());
        key = Keys.hmacShaKeyFor(decodedKey);
    }


    /**
     * 토큰 생성
     */
    private String createToken(Claims claims, long validTime) {
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + validTime);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireTime)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 토큰 만료 시간 체크
     */
    public boolean validateJwtTokenExpireTime(String jwtToken) {
        if (jwtToken == null) {
            return false;
        }

        try {
            Claims claims = parseClaimsFromJwtToken(jwtToken);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 엑세스 토큰 생성
     */
    public String createNewAccessToken(Claims claims) {
        return this.createToken(claims, ONE_HOUR_IN_MILLISECONDS);
    }

    public String createAccessToken(Claims claims) {
        return  this.createToken(claims, ONE_HOUR_IN_MILLISECONDS);
    }

    public String createRefreshToken(Claims claims) {
        return createToken(claims, ONE_MONTH_IN_MILLISECONDS);
    }

    /**
     * 토큰 -> Authentication
     */
    public Authentication getAuthentication(String jwtToken) {
        UserDetails userInfo;
        String userId = this.getUserId(jwtToken);

        userInfo = authDelegate.loadUserByUsername(userId);

        return new UsernamePasswordAuthenticationToken(userInfo, "", userInfo.getAuthorities());
    }

    /**
     * 토큰 -> Claims 파싱
     */
    private Claims parseClaimsFromJwtToken(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

    }

    /**
     * 토큰 -> 회원 정보 추출
     */
    public String getUserId(String token) {
        return this.parseClaimsFromJwtToken(token).getSubject();
    }

    /**
     * 토큰 -> email 추출
     */
    public String getEmail(String token){
        return (String) this.parseClaimsFromJwtToken(token).get(EMAIL);
    }

}
