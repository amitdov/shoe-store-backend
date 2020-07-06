package store.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class QueryData<T> {
    Integer totalCount;
    String nextPage;
    List<T> data;
}
