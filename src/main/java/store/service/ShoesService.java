package store.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import store.entities.QueryData;
import store.entities.Shoes;
import org.springframework.stereotype.Service;
import store.utils.SendHttpRequests;

import java.util.List;
import java.util.Optional;

@Service
public class ShoesService {
    public static final int DEFAULT_LIMIT = 16;
    public static final int STARTING_OFFSET = 0;
    public static final String AND_CHARACTER = "&";
    public static final String PRICE_FILTER_START = "filter=price:[";
    public static final String PRICE_FILTER_END = "],priceCurrency:USD";
    public static final String MAX_PRICE_ADDITION = "..";
    public static final String CATEGORY_IDS_FILTER = "category_ids=";
    public static final String QUERY_FILTER = "q=";
    public static final String SPACE_STRING = " ";
    public static final String LIMIT_FILTER = "limit=";
    public static final String OFFSET_FILTER = "offset=";
    public static final String TOTAL_FIELD_NAME = "total";
    public static final String ITEMS_FIELD_NAME = "itemSummaries";
    public static final String NEXT_PAGE_FIELD_NAME = "next";
    public static final int DEFAULT_CATEGORY = 3034;

    @Autowired
    private SendHttpRequests<JsonObject> sendHttpRequests;
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Shoes.class, new ShoesDeserializer())
            .create();

   static final String apiAddress ="https://api.ebay.com/buy/browse/v1/item_summary/search?";

    public QueryData<Shoes> search(String queryText, Integer category,Integer limit, Integer offset){
        String request = handleSimpleRequestData(queryText, category);
        request =handleLimitAndOffset(limit,offset,request);
        return getShoesByRequest(request);
    }



    public QueryData<Shoes> complexSearch(String queryText, Integer category,
                                          Double startPrice, Double endPrice,
                                          String color, String brand,
                                          Integer limit, Integer offset) {
        String request = handleSimpleRequestData(queryText, category,color,brand);
        request = handlePriceData(startPrice, endPrice, request);
        request =handleLimitAndOffset(limit,offset,request);
        return getShoesByRequest(request);
    }

    public QueryData<Shoes> nextPage(String hRef) {
        return getShoesByRequest(hRef);
    }

    private String handlePriceData(Double startPrice, Double endPrice, String request) {
        if(startPrice != null || endPrice !=null){
            request+=AND_CHARACTER + PRICE_FILTER_START;
            if(startPrice != null){
                request+=startPrice;
            }
            if(endPrice != null){
                request+= MAX_PRICE_ADDITION +endPrice;
            }
            request+= PRICE_FILTER_END;
        }
        return request;
    }

    private String handleSimpleRequestData(String queryText, Integer category,String color, String brand) {
        String request = handleCategory(category);

        if(((queryText != null)&&(!queryText.isEmpty()))||
                (color != null) ||
                (brand != null)){
            request += AND_CHARACTER+ QUERY_FILTER;
            boolean isFirst = true;
            if (queryText != null) {
                request +=queryText;
                isFirst=false;
            }
            if(color != null){
                if(!isFirst){
                    request+= SPACE_STRING;
                }
                request+= color;
            }
            if(brand != null){
                if(!isFirst){
                    request+=SPACE_STRING;
                }
                request+=brand;
            }
        }

        return request;
    }

    private String handleSimpleRequestData(String queryText, Integer category) {
        String request = handleCategory(category);

        if (queryText != null) {
            request += AND_CHARACTER+QUERY_FILTER + queryText ;
        }

        return request;
    }

    private String handleCategory(Integer category) {
        if (category == null) {
            category = DEFAULT_CATEGORY;
        }
        return apiAddress + CATEGORY_IDS_FILTER + category;
    }

    private String handleLimitAndOffset(Integer limit, Integer offset, String request) {

        request +=AND_CHARACTER+ LIMIT_FILTER + Optional.ofNullable(limit).orElse(DEFAULT_LIMIT)+
                AND_CHARACTER + OFFSET_FILTER +Optional.ofNullable(offset).orElse(STARTING_OFFSET);
        return request;
    }

    private QueryData<Shoes> getShoesByRequest(String request) {
        ResponseEntity<String> responseEntity = sendHttpRequests.execute(request , HttpMethod.GET, null);

        if(responseEntity.getStatusCode().isError()){
            // print response body
            System.out.println(responseEntity.getStatusCode().getReasonPhrase());
            return null;
        }
        else{
            JsonObject body = gson.fromJson(responseEntity.getBody(), JsonObject.class);
            int totalCount = body.get(TOTAL_FIELD_NAME).getAsInt();
            if(totalCount==0){
                return null;
            }else{
                JsonArray itemsArray = body.get(ITEMS_FIELD_NAME).getAsJsonArray();
                List<Shoes> list = gson.fromJson(itemsArray,new TypeToken<List<Shoes>>(){}.getType());
                return new QueryData<>(totalCount, body.get(NEXT_PAGE_FIELD_NAME).getAsString(), list);
            }
        }
    }

    /*
     * Try to add aspect filter as specified on the ebay manual but it's not working
     */
//    private String HandleColor(String color, String request) {
//        // adding the color and the brand to the string query
//        if(color != null){
////            request+="&aspect_filter=categoryId:"+category+",Color:{"+color+"}";
//        }
//
//        return request;
//    }
}
