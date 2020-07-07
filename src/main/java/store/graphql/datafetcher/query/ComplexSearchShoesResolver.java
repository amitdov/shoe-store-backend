package store.graphql.datafetcher.query;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import store.entities.QueryData;
import store.entities.Shoes;
import store.service.ShoesService;


@Controller
public class ComplexSearchShoesResolver implements DataFetcher<QueryData<Shoes>> {

    @Autowired
    private ShoesService shoesServiceService;

    @Override
    public QueryData<Shoes> get(DataFetchingEnvironment environment)  {
        String queryText = environment.getArgument("queryText");
        Integer category = environment.getArgument("category");
        Double startPrice = environment.getArgument("startPrice");
        Double endPrice = environment.getArgument("endPrice");
        String color = environment.getArgument("color");
        String brand = environment.getArgument("brand");
        Integer limit = environment.getArgument("limit");
        Integer offset = environment.getArgument("offset");
        return shoesServiceService.complexSearch(queryText,category,startPrice,endPrice,color,brand,limit,offset);
    }
}
