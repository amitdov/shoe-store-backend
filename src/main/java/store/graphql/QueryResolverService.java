package store.graphql;

import store.graphql.datafetcher.query.ComplexSearchShoesResolver;
import store.graphql.datafetcher.query.NextPageResolver;
import store.graphql.datafetcher.query.ShoesResolver;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QueryResolverService {
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
        queryDataFetchers.put("searchShoes", shoesResolver);
        queryDataFetchers.put("complexSearch", complexSearchShoesResolver);
        queryDataFetchers.put("nextPage", nextPageResolver);
        return queryDataFetchers;
    }
}
