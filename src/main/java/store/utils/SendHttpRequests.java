package store.utils;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("ALL")
@Component
public class SendHttpRequests<T> {
    static final String token ="\"v^1.1#i^1#I^3#p^1#r^0#f^0#t^H4sIAAAAAAAAAOVYa2wUVRTudvsIoUUTniFClqlgxMzsnZ2d3Z0Ju7hsaVmgD7ql1Y3Y3Jm52047O7Ode5d2kcS2JhB8JERIDD5i/5gYCBFC8BEQH6n8ICQKKIIxKvJDQwJYjRIxgDPbpWwr8uoamrh/NnPuued+5/vOuffOgL6yKYs3rdh0qdJRXjzYB/qKHQ52KphSVvrYNGfx3NIikOfgGOx7uK9kwPnzEgyTWkpsQjhl6Bi5epOajsWsMUilTV00IFaxqMMkwiKRxVi4brXoYYCYMg1iyIZGuaLVQYrnBM7j9QI2wXPAK7OWVb8es9kIUtaoICvQByRB8QOBs8YxTqOojgnUSZDyAA+ggZ8GfDMbEHm/yPMML/jilKsFmVg1dMuFAVQoC1fMzjXzsN4aKsQYmcQKQoWi4ZpYQzhavby+eYk7L1Yox0OMQJLGY58ihoJcLVBLo1svg7PeYiwtywhjyh0aWWFsUDF8Hcw9wM9SjSRF4SXBw/vYAPT5CkNljWEmIbk1DtuiKnQi6yoinagkcztGLTakTiST3FO9FSJa7bL/1qShpiZUZAap5cvCT4YbG6kQkmDGHwiwkJZYTqK9UPHSkJN8dMCLuICP9wNOyi0yEilH8bhVIoauqDZh2FVvkGXIQozG8+LN48VyatAbzHCC2Gjy/DxglD82bgs6omCadOi2pihpkeDKPt6e/dHZhJiqlCZoNML4gSw9QQqmUqpCjR/M1mGudHpxkOogJCW63T09PUwPxxhmu9sDAOt+om51TO5ASUjlfO1e78Xq7SfQajYVGVkzsSqSTMrC0mvVqQVAb6dCrMfr93A53sfCCo23/sOQl7N7bDcUqju8XlYQBIUFwMNKEIFCdEcoV6BuG4ddo3QSml2IpDQoI1q26iydRKaqiByf8HCBBKIVn5CgvUIiQUu84qPZBEIAIUmShcD/pUnutMxjSDYRKVidF6TG+bSv3hI77Gn3siTQE8i0moHY+tamSLy1aQ23ga1tzsQ9pKPbCwLBO+2EmycvGynUaGiqnCkgA3avF4AFzlQaoUkyMaRplmFCiWI70cklsj0fWwFgSmXsnmJkI+k2oLWb26a2LOIJ5RxOpaLJZJpASUPRwu3k92EXv2l6qnXHmVQ5WfqNCKkqI5cTJqsmg9fLjImwkTatexnTYJ/XzUYX0q0dkJiGpiGzhZ2w0PdLX7vX/4WPuzgo7i3vwt5QJktdy5pqlU/bZMvsP1dThZPsFGZ5gRP8vNfPTyivSFbP5sxkO39WGJgg5W4u0iX+O7xKu8e+1IeKsj92wLEfDDj2FjscwA0WslVgQZlzbYmzYi5WCWJUmGCw2q5b76omYrpQJgVVs7jMkVoLzy3M+4wwuA7MGf2QMMXJTs37qgAeujFSyj4wu9KixA94NsD7eT4Oqm6MlrCzSmaccLQcT4bPf7d5+tWZu+NzV8XBzDioHHVyOEqLSgYcRdPc04eWHtw5vOu52i1fCeV0/OQHruOLrj219XNtON6/oXalo1UnzlPyqm8uVf5JTzvR+pIcPTT/l993zq7dc3l+5K/ZrkhTf8Nn56t2v3t4CL82i7/2ydfPDpdvDh6pWdo5/czHh9ZRZXPoN7v3nD174Y9uffun6nD1vi3HQh/9sI8ET+/48oVv3zrQX7565aOnti6INMpNfWjF5YHLJ9/bcajiwox3fu26eKWupeunqh8HZ71dvqi7bmjjvA+vvfjMvKVtZ47uYo89f/SIcLGtJFHx4MZHCPy+6kpw+NXT7oqqwcX84Q2/eWJnDnQyVxc7H9//+hv9nae3nXtFYZW9ZTWXnj44dPSL97e/fHDtiHx/A34OLBrgEQAA\"";
    private final RestTemplate rest = new RestTemplate();

    public ResponseEntity<String> execute(String url, HttpMethod method, String json) {
        HttpHeaders headers = initHeaders();
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = rest.exchange(url, method, entity, String.class);
        return response;
    }

    private HttpHeaders initHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        return headers;
    }

}