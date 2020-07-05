package store.entities;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shoes {
    String id;
    Price price;
    String pictureLink;
    String name;
}
