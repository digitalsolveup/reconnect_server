package reconnect.server.domain.auth.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.domain.auth.model.dto.Profile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoAccount {
    private Profile profile;
    private String email;
    private String gender;
    private String birthday;
    private String birthyear;
    private String ageRange;
}
