package reconnect.server.domain.search.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reconnect.server.global.model.enums.Gender;
import reconnect.server.global.model.enums.SpecialFeature;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    private String name;
    private Gender gender;
    private SpecialFeature specialFeature;
    private Integer age;
    private LocalDate startDate;
    private LocalDate endDate;
    private String lastSeenLocation;
}
