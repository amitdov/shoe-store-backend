package store.service;

import com.google.gson.*;
import store.entities.Price;
import store.entities.Shoes;

import java.lang.reflect.Type;
import java.util.Optional;

public class ShoesDeserializer implements JsonDeserializer<Shoes> {

    public static final String PRICE_FIELD_NAME = "price";
    public static final String ITEM_ID_FIELD_NAME = "itemId";
    public static final String TITLE_FIELD_NAME = "title";
    public static final String IMAGE_FIELD_NAME = "image";
    public static final String IMAGE_URL_FIELD_NAME = "imageUrl";
    public static final String PRICE_VALUE_FIELD_NAME = "value";
    public static final String PRICE_CURRENCY_FIELD_NAME = "currency";

    @Override
        public Shoes deserialize(JsonElement json, Type typeOfT,
                                 JsonDeserializationContext context)
                             throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonObject price = jsonObject.get(PRICE_FIELD_NAME).getAsJsonObject();
            return Shoes
                    .builder()
                    .id(jsonObject.get(ITEM_ID_FIELD_NAME).getAsString())
                    .name(jsonObject.get(TITLE_FIELD_NAME).getAsString())
                    .pictureLink(Optional.ofNullable(jsonObject.get(IMAGE_FIELD_NAME))
                            .map(JsonElement::getAsJsonObject)
                            .map(image->image.get(IMAGE_URL_FIELD_NAME))
                            .map(JsonElement::getAsString)
                            .orElse(null))
                    .price(Price
                            .builder()
                            .value(price.get(PRICE_VALUE_FIELD_NAME).getAsDouble())
                            .currency(price.get(PRICE_CURRENCY_FIELD_NAME).getAsString())
                            .build())

                    .build();
        }
}
