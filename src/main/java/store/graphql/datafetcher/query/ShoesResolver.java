package store.graphql.datafetcher.query;

import store.entities.Shoes;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import store.service.SearchShoes;

import java.util.List;

@Controller
public class ShoesResolver implements DataFetcher<List<Shoes>> {

    @Autowired
    private SearchShoes searchShoesService;

    @Override
    public List<Shoes> get(DataFetchingEnvironment environment) throws Exception {
        String queryText = environment.getArgument("queryText");
        Integer category = environment.getArgument("category");
        return searchShoesService.search(queryText,category);
    }
}
