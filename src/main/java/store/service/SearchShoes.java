package store.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import store.entities.Price;
import store.entities.Shoes;
import org.springframework.stereotype.Service;
import store.utils.SendHttpRequests;

import java.util.Collections;
import java.util.List;

@Service
public class SearchShoes {
    @Autowired
    private SendHttpRequests<JsonObject> sendHttpRequests;
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Shoes.class, new ShoesDeserializer())
            .create();

   static final String apiAddress ="https://api.ebay.com/buy/browse/v1/item_summary/search?q=";

    public List<Shoes> search(String queryText, String category){
        if(queryText ==null){
            queryText ="harry potter";
        }

        ResponseEntity<String> responseEntity = sendHttpRequests.execute(apiAddress+ queryText, HttpMethod.GET, null);

        if(responseEntity.getStatusCode().isError()){
            // print response body
            System.out.println(responseEntity.getStatusCode().getReasonPhrase());
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
        return Collections.singletonList(new Shoes("dcd", Price.builder().currency("USD").value(58.3).build(),"ddds","sds"));
    }


}
