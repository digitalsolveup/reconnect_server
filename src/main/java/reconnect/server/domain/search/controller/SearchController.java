package reconnect.server.domain.search.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reconnect.server.domain.search.model.request.SearchRequest;
import reconnect.server.domain.search.model.response.SearchResponse;
import reconnect.server.domain.search.service.SearchService;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/search")
@Tag(name = "Search", description = "실종자 검색 관련 컨트롤러")
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "실종자 검색", description = """
             이름, 성별, 특이사항, 나이, 실종 시작일 - 종료일, 실종시간으로 필터링 및 검색 가능
             이름 검색 + 필터링 둘다 동시 작동을 위해 하나의 Controller 에서 기능 처리
            """)
    @GetMapping("")
    public Page<SearchResponse> getSearchInfo(SearchRequest searchRequest, Pageable pageable) {
        return searchService.getSearchInfo(searchRequest, pageable);
    }
}
