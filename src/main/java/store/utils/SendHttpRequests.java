package store.utils;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("ALL")
@Component
public class SendHttpRequests<T> {
    static final String token ="\"v^1.1#i^1#p^1#I^3#r^0#f^0#t^H4sIAAAAAAAAAOVYe2wURRjv9doqYJWHUUIwaRcJ8bF7s3t7r4VeuD6AM6WvK6VcEJzdnWvH3u2uO3MtZ9SUgugfEBIiBhLEQgwxMSgIGCMowZgYAjFKRAmCgsZERSNgIiSgcfZ6lGslvHpqE++fy37zzTe/7/f7vpnZBb1lYx5eNW/VhXLXHcX9vaC32OUSx4ExZaWP3O0unlJaBPIcXP29D/aW9Ll/mEVgKmkpLYhYpkFQxbJU0iBK1ljFpW1DMSHBRDFgChGFakosMr9ekQSgWLZJTc1MchXR2ipO13wJ3Qd1GIQJVYPMaFwJ2WpWcSGgySGoylAXA0gKSGyckDSKGoRCg1ZxEpAADwI88LeKIcUnK8AvBLzeOFfRhmyCTYO5CIALZ9Eq2bl2HtTrI4WEIJuyIFw4GpkTa4xEa+saWmd58mKFczTEKKRpMvSpxtRRRRtMptH1lyFZbyWW1jRECOcJD6wwNKgSuQLmNuBnmYa6rAYkvxqCCPgBEgtC5RzTTkF6fRyOBet8IuuqIINimrkRo4wN9Umk0dxTAwsRra1w/prTMIkTGNlVXF11ZFGkqYkLIxVmAsGgCHlV9Ko8qxaZh17Vzwdl5A36fQHgVXOLDETKUTxslRrT0LFDGKloMGk1YojRcF7kPF6YU6PRaEcS1EGT5yeJV/iTAnFH0AEF07TTcDRFKUZCRfbxxuwPzqbUxmqaosEIwwey9DCdLQvr3PDBbB3mSmcZqeI6KbUUj6enp0fo8Qqm3eGRABA97fPrY1onSrFGzPk6vU7wjSfwOJuKhthMghWasRiWZaxOGQCjgwuLkhyQvDneh8IKD7f+zZCXs2doNxSqO1jNhPQA0EWIZE2W5UJ0RzhXoB4Hh1OjfAraXYhaSaghXmN1lk4hG+uK15eQvMEE4nV/KMHLoUSCV326nxcTCAGEVFULBf8vTXKzZR5Dmo1o4eq8EDXuS/sbmNgRqUMWabAnmFloB2PdC1tq4gtbmr1Pi3NbM3GJdj4lg2DVzXbCtZPXTAs1mUmsZQrJgNPrI2fBa+tN0KaZGEommWFEiRIn0dElsjOfsADQwoLTU4JmpjwmZLu5Y1qaRTyinCOWFU2l0hSqSRQt4E7+7+/i10wPszvOqMqJ6TcgJNYHLidCVk2BdGuCjYiZttm9TGh0zutWswsZbAektplMIrtNHLHQ/5m+Tq9fm49bOChuL+8C31BGSV1rSczKZ+loy+wfVxPDUXYKi76QDPw+SfaPKK+arJ6tmdF2/swzCUX6LV2kS/w3d5X2DH2nDxdlf2Kfaw/oc+0sdrmAB0wXp4HKMveCEvddUwimSMAwIRDcYbB3VRsJXShjQWwXl7msBfCn6XlfEfofB5MHvyOMcYvj8j4qgKlXR0rFe+4vZ5QEgF8M+ZiScTDt6miJeF/JvZPWT5g3/mTZodjZ/ac2qWtqNm3DH4DyQSeXq7SopM9V5F3cfn7KRP/h41sPCXtOTty2N1J5qmniOXR8rRnXLu21frkwadGHT8zYuHnHpQvNE3Y07rr8/J6vj73zu9wn8ysfONEyt76c1G3Y0Lux6UwwFZks03XL98cPhGcmLjaNTR6e+Q1+XX05wn25VF9y5Jj93ME3dm1/G7/4QigaT50+tWv2Q5/vqz6o1Xy0Ezw7U/lka2YJHlf5/Yzzm3/8eMWnj612/Tb7wPbvusu+ffdApbs2qFyGlRdTr56u/3PFM681dy/+ar3Ugle8tLu9ffmRM/1ubnxbZ+d7Ra/s7vr1ROlntYvfst9sPft+bM2d02qP1q1fd/GPlUd/Hr9FEqunPnrui/4lY/dtmY1X7x+Q7y8HZPk03xEAAA==\"";
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