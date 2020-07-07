import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import store.entities.QueryData;
import store.entities.Shoes;
import store.service.ShoesService;
import store.utils.SendHttpRequests;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoesServiceTest {
    @Mock
    SendHttpRequests sendHttpRequests;

    @InjectMocks
    ShoesService shoesService;

    public static final Integer TOTAL_COUNT = 223417;
    static final String EBAY_RESPONSE_STRING = "{\n" +
            "    \"href\": \"https://api.ebay.com/buy/browse/v1/item_summary/search?q=+Red+Nike&limit=50&offset=0\",\n" +
            "    \"total\": " + TOTAL_COUNT + ",\n" +
            "    \"next\": \"https://api.ebay.com/buy/browse/v1/item_summary/search?q=+Red+Nike&limit=50&offset=50\",\n" +
            "    \"limit\": 50,\n" +
            "    \"offset\": 0,\n" +
            "    \"itemSummaries\": [\n" +
            "        {\n" +
            "            \"itemId\": \"v1|124242003279|425098438312\",\n" +
            "            \"title\": \"Nike Air Jordan Retro 14 GYM RED \\\"TORO\\\" VI 2020 PREORDER *READ DESCRIPTION*\",\n" +
            "            \"image\": {\n" +
            "                \"imageUrl\": \"https://i.ebayimg.com/thumbs/images/g/FlsAAOSwJ3te-2~k/s-l225.jpg\"\n" +
            "            },\n" +
            "            \"price\": {\n" +
            "                \"value\": \"339.99\",\n" +
            "                \"currency\": \"USD\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"itemId\": \"v1|124231909959|425087213360\",\n" +
            "            \"title\": \"Nike Air Max 270 React Running Shoes Noble Red Team Gold AO4971-601 Men's NEW\",\n" +
            "            \"image\": {\n" +
            "                \"imageUrl\": \"https://i.ebayimg.com/thumbs/images/g/r6cAAOSwLB5edVZx/s-l225.jpg\"\n" +
            "            },\n" +
            "            \"price\": {\n" +
            "                \"value\": \"75.99\",\n" +
            "                \"currency\": \"USD\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"itemId\": \"v1|293614335193|592434368692\",\n" +
            "            \"title\": \"Nike Air Barrage Mid University Red AT7847 102\",\n" +
            "            \"image\": {\n" +
            "                \"imageUrl\": \"https://i.ebayimg.com/thumbs/images/g/364AAOSw-gBe54-W/s-l225.jpg\"\n" +
            "            },\n" +
            "            \"price\": {\n" +
            "                \"value\": \"159.99\",\n" +
            "                \"currency\": \"USD\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"itemId\": \"v1|233611966560|533299904879\",\n" +
            "            \"title\": \"Nike Air Max Plus Tn Sunburst Habanero Red Orange Gold Black CK9393-600 Men's\",\n" +
            "            \"image\": {\n" +
            "                \"imageUrl\": \"https://i.ebayimg.com/thumbs/images/g/w~EAAOSwH2Be84j0/s-l225.jpg\"\n" +
            "            },\n" +
            "            \"price\": {\n" +
            "                \"value\": \"124.97\",\n" +
            "                \"currency\": \"USD\"\n" +
            "            }\n" +
            "        }]}";



    @Test
    /**
     * Check that the parsing is working
     */
    public void simpleSearch(){
        when(sendHttpRequests.execute("https://api.ebay.com/buy/browse/v1/item_summary/search?category_ids=3034&q=amit&limit=16&offset=0", HttpMethod.GET,null))
                .thenReturn(ResponseEntity.of(Optional.of(EBAY_RESPONSE_STRING)));

        QueryData<Shoes> results = shoesService.search("amit", null, null, null);

        Assert.assertEquals(TOTAL_COUNT,results.getTotalCount());
        Assert.assertEquals(4,results.getData().size());
    }
}
