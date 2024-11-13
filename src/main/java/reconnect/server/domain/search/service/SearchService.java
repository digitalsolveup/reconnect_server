package reconnect.server.domain.search.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reconnect.server.domain.search.model.request.SearchRequest;
import reconnect.server.domain.search.model.response.SearchResponse;
import reconnect.server.domain.search.repository.SearchQueryRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchQueryRepository searchQueryRepository;

    public Page<SearchResponse> getSearchInfo(SearchRequest searchRequest, Pageable pageable) {
        return searchQueryRepository.getSearchInfo(searchRequest, pageable).map(SearchResponse::new);
    }
}
