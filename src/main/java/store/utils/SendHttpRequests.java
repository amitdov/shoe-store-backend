package store.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("ALL")
@Component
public class SendHttpRequests<T> {
    @Value("${token}")
    String token;
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