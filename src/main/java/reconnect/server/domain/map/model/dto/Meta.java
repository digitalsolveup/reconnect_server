package reconnect.server.domain.map.model.dto;

import lombok.Data;

@Data
public class Meta {
    private boolean is_end;       // 마지막 페이지 여부
    private int pageable_count;   // 전체 페이지 수
    private int total_count;      // 총 검색 결과 수
}
