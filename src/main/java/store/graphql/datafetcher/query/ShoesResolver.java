package store.graphql.datafetcher.query;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import store.entities.QueryData;
import store.entities.Shoes;
import store.service.ShoesService;

@Controller
public class ShoesResolver implements DataFetcher<QueryData<Shoes>> {

    @Autowired
    private ShoesService shoesServiceService;

    @Override
    public QueryData<Shoes> get(DataFetchingEnvironment environment) {
        String queryText = environment.getArgument("queryText");
        Integer category = environment.getArgument("category");
        Integer limit = environment.getArgument("limit");
        Integer offset = environment.getArgument("offset");
        return shoesServiceService.search(queryText,category,limit,offset);
    }
}
