package store.graphql.datafetcher.query;

import store.entities.Shoes;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import store.service.ShoesService;

import java.util.List;

@Controller
public class ShoesResolver implements DataFetcher<List<Shoes>> {

    @Autowired
    private ShoesService shoesServiceService;

    @Override
    public List<Shoes> get(DataFetchingEnvironment environment) throws Exception {
        String queryText = environment.getArgument("queryText");
        Integer category = environment.getArgument("category");
        Integer limit = environment.getArgument("limit");
        Integer offset = environment.getArgument("offset");
        return shoesServiceService.search(queryText,category,limit,offset);
    }
}
