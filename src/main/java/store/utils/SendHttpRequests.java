package store.utils;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("ALL")
@Component
public class SendHttpRequests<T> {
    static final String token ="\"v^1.1#i^1#r^0#p^1#I^3#f^0#t^H4sIAAAAAAAAAOVYa2wUVRTuPlpTy8MIKmmMrlOBiM7unZl9TIfuJkt5rdIH3aXAgql3Zu+0I7szw9y7bBeVtNWgiWCiMRKCQTC1NpFgQAloQgIxRgOBEOMjwg9jlMRAoBA1lRSNM9OlbAvh1TU0cf9s5txzz/3O951z750BXRWVczYu3jg42XGPc0cX6HI6HEwVqKwof3KKy1ldXgaKHBw7uh7vcve4fqvDMJPWhRaEdU3FyNOZSatYsI1hKmuoggaxggUVZhAWiCTEow1LBNYLBN3QiCZpacoTmx+mgozEyiEYlGQRsBzLm1b1SsyEFqYYkWVkFAj5WYSCQObMcYyzKKZiAlUSpljAAhqEaBBMgKDg5wTAeblaf5LytCIDK5pqungBFbHhCvZcowjrjaFCjJFBzCBUJBZdGG+KxuYvaEzU+YpiRQo8xAkkWTz6qV5LIU8rTGfRjZfBtrcQz0oSwpjyRYZXGB1UiF4BcwfwbapTIV5kWZGXUyxMhSSpJFQu1IwMJDfGYVmUFC3brgJSiULyN2PUZEN8Hkmk8NRohojN91h/S7MwrcgKMsLUgnnRldHmZiqCRJgP8TwDaZHhRNoPU34acmKQ5v2I44OBEODEwiLDkQoUj1mlXlNTikUY9jRqZB4yEaOxvHBFvJhOTWqTEZWJhabYjx/hj0lagg4rmCUdqqUpypgkeOzHm7M/MpsQQxGzBI1EGDtg0xOmoK4rKWrsoF2HhdLpxGGqgxBd8PlyuZw3x3k1o93HAsD4VjQsiUsdKAOpgq/V651YufkEWrFTkZA5EysCyesmlk6zTk0AajsVYVh/iOUKvI+GFRlrvcZQlLNvdDeUqjuQxJjtAUMBLiQjWIreiBTK02ehsCqUzkBjDSJ6GkqIlswqy2aQoaQELiCzHC8jOhWslWl/rSzTYiAVpM2NDwGERFGq5f8vLXKrRR5HkoFIyaq8JBUeyAYbTbGjbLufIXyOzy83+Pi65S31yeUtS7n1zKJEPsmSjrV+wIdvtQ+un7yk6ahZSytSvqQMWL0+bhY4I9UMDZKPo3TaNIwrUWwlOrFEtuZjMwDUFa/VU15Jy/g0aO7llqnNRjyunKO6HstksgSKaRQr3T5+F/bw66anmDecCZWTqd+wkEpq+GritdX04nWS10BYyxrmrczbZJ3WCW0NUs0dkBhaOo2MVmbcQt89fQvn+rV83MZBcWd5l/Z+MlHqWkorZvm0TbTM/nM1FTjBTmEmUOsHTCjEj0/PelvPRH6inT+LNUxQ6vau0e7ALV2kfaNf6SNl9o/pcewDPY49TocD+MBMpgY8VuFa5nZNqsYKQV4Fyl6stKvmm6qBvGtQXoeK4axw6MvgmZlFHxF2PAtmjHxGqHQxVUXfFMDDV0fKmakPTTYpCYEgCPo5wCVBzdVRN/Oge3pNs9st13yw9ZcL54/Pbjz4/baBf4bA5BEnh6O8zN3jKMu9Pgjv3xj9/KivUz68XX21v/KB5g2124/vf2WWenD/wFNCZ2bx3HvPO3u27V61el9T68uPzEusfHdg11v4jVMr5MGdm/dWfyx1LWiY9lVD5G/XfZsmxXKtPwcuvT3ly4vugSOBFVvW7zvhvnT55Gt93BeV5/a/s2Rq+9J4tPr0pnSAPFddH9pV1vNr/+ahLVsfnfF199OuT9c+cfb3Y50LV/Wt73Nf/nZ6sndP997X3ztz+NiiwIFtn0yK6vxPPwiH+i/3ffMSfP/FI71nq9p6m3pPXJQOnTzn3JCXund/6El21/2YHJr1mfHRhlPftfVvb/irRTq8eu7Q7D8GX6g5Pa3sQtUzR+fkXHV/Htj55rB8/wI7lH2N3hEAAA==\"";
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