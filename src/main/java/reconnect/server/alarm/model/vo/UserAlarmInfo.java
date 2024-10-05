package reconnect.server.alarm.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAlarmInfo {
    @Id
    public long _id;

    // 알림 제목
    public String title;

    // 알림 내용
    public String content;

    // Redirect url
    public String redirectUrl;

    // 생성 시간
    private long createTime;

    // 전송 시간
    private long sendTime;

    private Boolean isRead;

    private Boolean isDeleted;

    public void setDefaultStatus(){
        this.setIsDeleted(false);
        this.setIsRead(false);
    }
}

