package reconnect.server.global.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reconnect.server.domain.map.model.response.LocationInfoListResponse;

@FeignClient(name = "googleClient", url = "${google.api.map.url}")
public interface GoogleMapClient {

    @GetMapping
    LocationInfoListResponse fetchLocations(
            @RequestParam("location") String location,
            @RequestParam("radius") int radius,
            @RequestParam("keyword") String keyword,
            @RequestParam("key") String apiKey
    );
}
