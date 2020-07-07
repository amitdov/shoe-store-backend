package store.graphql;

import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.graphql.datafetcher.query.ComplexSearchShoesResolver;
import store.graphql.datafetcher.query.NextPageResolver;
import store.graphql.datafetcher.query.ShoesResolver;

import java.util.HashMap;
import java.util.Map;

@Service
public class QueryResolverService {
    public static final String SEARCH_SHOES_QUERY_NAME = "searchShoes";
    public static final String COMPLEX_SEARCH_QUERY_NAME = "complexSearch";
    public static final String NEXT_PAGE_QUERY_NAME = "nextPage";
    /**
     * Queries
     */
    @Autowired
    ShoesResolver shoesResolver;

    @Autowired
    ComplexSearchShoesResolver complexSearchShoesResolver;

    @Autowired
    private NextPageResolver nextPageResolver;

    Map<String, DataFetcher> initQueryDataFetchers() {
        Map<String, DataFetcher> queryDataFetchers = new HashMap<>();
        queryDataFetchers.put(SEARCH_SHOES_QUERY_NAME, shoesResolver);
        queryDataFetchers.put(COMPLEX_SEARCH_QUERY_NAME, complexSearchShoesResolver);
        queryDataFetchers.put(NEXT_PAGE_QUERY_NAME, nextPageResolver);
        return queryDataFetchers;
    }
}
