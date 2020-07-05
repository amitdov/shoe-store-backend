package store.service;

import com.google.gson.*;
import store.entities.Price;
import store.entities.Shoes;

import java.lang.reflect.Type;
import java.util.Optional;

public class ShoesDeserializer implements JsonDeserializer<Shoes> {
        @Override
        public Shoes deserialize(JsonElement json, Type typeOfT,
                                 JsonDeserializationContext context)
                             throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonObject price = jsonObject.get("price").getAsJsonObject();
            return Shoes
                    .builder()
                    .id(jsonObject.get("itemId").getAsString())
                    .name(jsonObject.get("title").getAsString())
                    .pictureLink(Optional.ofNullable(jsonObject.get("image"))
                            .map(JsonElement::getAsJsonObject)
                            .map(image->image.get("imageUrl"))
                            .map(JsonElement::getAsString)
                            .orElse(null))
                    .price(Price
                            .builder()
                            .value(price.get("value").getAsDouble())
                            .currency(price.get("currency").getAsString())
                            .build())

                    .build();
        }
}
