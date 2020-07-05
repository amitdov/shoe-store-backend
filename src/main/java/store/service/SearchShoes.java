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

import java.net.http.HttpClient;
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
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

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
            JsonArray body = gson.fromJson(responseEntity.getBody(), JsonObject.class).get("itemSummaries").getAsJsonArray();
            List<Shoes> shoes = gson.fromJson(body,new TypeToken<List<Shoes>>(){}.getType());
            return shoes;
        }
        // Model the response
        return Collections.singletonList(new Shoes("dcd", Price.builder().currency("USD").value(58.3).build(),"ddds","sds"));
    }


}
