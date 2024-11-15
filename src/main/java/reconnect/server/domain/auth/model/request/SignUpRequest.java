package reconnect.server.domain.auth.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class SignUpRequest {

    // 이메일
    private String email;

    // 이름
    private String name;

    // 성별
    private String sex;

    // 생일
    private Date birth;

    // 프로필 이미지
    private String profileUrl;

    // 로그인 서비스 종류 - kakao, naver, google..
    private String provider;
}
