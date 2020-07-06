package store.graphql.datafetcher.query;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import store.entities.QueryData;
import store.entities.Shoes;
import store.service.ShoesService;

@Controller
public class NextPageResolver implements DataFetcher<QueryData<Shoes>> {

    @Autowired
    private ShoesService shoesServiceService;

    @Override
    public QueryData<Shoes> get(DataFetchingEnvironment environment) throws Exception {
        String hRef = environment.getArgument("hRef");
        return shoesServiceService.nextPage(hRef);
    }
}
