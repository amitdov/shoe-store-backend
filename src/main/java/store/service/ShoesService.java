package store.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import store.entities.Shoes;
import org.springframework.stereotype.Service;
import store.utils.SendHttpRequests;

import java.util.List;

@Service
public class ShoesService {
    @Autowired
    private SendHttpRequests<JsonObject> sendHttpRequests;
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Shoes.class, new ShoesDeserializer())
            .create();

   static final String apiAddress ="https://api.ebay.com/buy/browse/v1/item_summary/search?";

    public List<Shoes> search(String queryText, Integer category,Integer limit, Integer offset){

        String request = handleSimpleRequestData(queryText, category);

        return getShoesByRequest(request);
    }



    public List<Shoes> complexSearch(String queryText, Integer category, Double startPrice, Double endPrice, String color, String brand, Integer limit, Integer offset) {
        String request = handleSimpleRequestData(queryText, category);
        request = HandleColorAndBrandData(color,brand, request);
        request = HandlePriceData(startPrice, endPrice, request);

        return getShoesByRequest(request);
    }

    private String HandleColorAndBrandData(String color, String brand, String request) {
        // adding the color and the brand to the string query
        if(color != null){
//            request+="&aspect_filter=categoryId:"+category+",Color:{"+color+"}";
            request+=" "+ color;
        }
        if(brand != null){
            request+=" "+brand;
        }
        return request;
    }

    private String HandlePriceData(Double startPrice, Double endPrice, String request) {
        if(startPrice != null || endPrice !=null){
            request+="&filter=price:[";
            if(startPrice != null){
                request+=startPrice;
            }
            if(endPrice != null){
                request+=".."+endPrice;
            }
            request+="],priceCurrency:USD";
        }
        return request;
    }

    private String handleSimpleRequestData(String queryText, Integer category) {
        if (category == null) {
            category = 3034;
        }
        String request = apiAddress + "category_ids=" + category;
        if (queryText != null) {
            request += "&q=" + queryText ;
        }

        return request;
    }

    private List<Shoes> getShoesByRequest(String request) {
        ResponseEntity<String> responseEntity = sendHttpRequests.execute(request , HttpMethod.GET, null);

        if(responseEntity.getStatusCode().isError()){
            // print response body
            System.out.println(responseEntity.getStatusCode().getReasonPhrase());
            return null;
        }
        else{
            JsonObject body = gson.fromJson(responseEntity.getBody(), JsonObject.class);
            if(body.get("total").getAsInt()==0){
                return null;
            }else{
                JsonArray itemsArray = body.get("itemSummaries").getAsJsonArray();
                return gson.fromJson(itemsArray,new TypeToken<List<Shoes>>(){}.getType());
            }
        }
        // Model the response
//        return Collections.singletonList(new Shoes("dcd", Price.builder().currency("USD").value(58.3).build(),"ddds","sds"));
    }
}
