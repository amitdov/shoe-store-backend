package store.utils;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("ALL")
@Component
public class SendHttpRequests<T> {
    static final String token ="\"v^1.1#i^1#I^3#p^1#r^0#f^0#t^H4sIAAAAAAAAAOVYXWwUVRTudrdtChZjQFHEuA5iasnM3pnZnd0d2aVLW+gKtKW7ZaWi5M7M3e7Y3Zlx7t226wulRohI7IOBCA9S4wNRNNEHjVGDIjWFRGKARGMIifiHQKKJQUDAn5ntUrYV+esamrgvmzn33HO/833n3HtnQH9ldd2G5g1naxxV5UP9oL/c4WCng+rKigUznOVzKspAkYNjqP/BfteA86eFGGbShtiOsKFrGLn7MmkNi3ljiMqamqhDrGJRgxmERSKLsciK5SLHANEwdaLLeppyRxtDFI8CQQRQEEBBULigz7Jql2LG9RAVlDmQ5IN+r98L2aSArHGMsyiqYQI1EqI4wAEa+GkgxFlW9Amij2UEX7CTcq9CJlZ1zXJhABXOwxXzc80irFeHCjFGJrGCUOFoZEmsNRJtbGqJL/QUxQoXeIgRSLJ4/FODriD3KpjOoqsvg/PeYiwrywhjyhMeXWF8UDFyCcxNwM9T7Qcyx0FFDrAslCUklITKJbqZgeTqOGyLqtDJvKuINKKS3LUYtdiQnkIyKTy1WCGijW77b2UWptWkiswQ1bQ4sjrS1kaFkQRz/kCAhbTE8hLthYqXhrwk0AEv4gOCzw94qbDIaKQCxRNWadA1RbUJw+4WnSxGFmI0kRdvES+WU6vWakaSxEZT7MeP8efrtAUdVTBLUpqtKcpYJLjzj9dmf2w2IaYqZQkaizBxIE9PiIKGoSrUxMF8HRZKpw+HqBQhhujx9Pb2Mr08o5tdHg4A1vPYiuUxOYUykCr42r3eh9VrT6DVfCqy1aKWv0hyhoWlz6pTC4DWRYVZzuvn+ALv42GFJ1r/YSjK2TO+G0rWHX4JBGTACqzkFYK8UoruCBcK1GPjsGuUzkCzGxEjDWVEy1adZTPIVBWR9yU5PpBEtCIEk7Q3mEzSkk8RaDaJrM0RSZIcDPxfmuR6yzyGZBORktV5SWrclxVaLLEjXJeXJYHeQC5hBmI9ifaGzkT7Sv4Zdmk818mR1NNeEAhdbydcOXlZN1CbnlblXAkZsHu9BCzwptIGTZKLoXTaMkwqUWwnOrVEtudjKwA0VMbuKUbWMx4dWru5bVqbRzypnCOGEc1ksgRKaRQt3U5+C3bxK6anWnecKZWTpd+okKoyejlh8moyuEdmTIT1rGndy5hW+7yO691Is3ZAYurpNDJXsZMW+lbpa/f6v/BxAwfFzeVd2hvKVKlrOa1a5bN2qmX2n6upwil2CrO+oBfwgsBOrjsb8nrGc1Pt/GnWMUHKjVykXf7rvEp7xr/Uh8vyP3bA8S4YcLxT7nAAD5jPzgMPVDo7XM7b5mCVIEaFSQarXZr1rmoiphvlDKia5ZUOowOenF/0GWHoCXD32IeEaic7veirAph7eaSCvX12jUWJH1gK+gQf2wnmXR51sXe5Zt13XHmZaokOn3PcceQY470/MrBzGNSMOTkcFWWuAUdZQ/WFt1bj7sUHjv0+11VbUbcI1YFvorsOJr/+rOvbNTv0kfra9nu+UM9trT8JHtp72pdq3vLkJ2WDtXL2y6NHpnW8yeyG4SXfH3hjzftLh+s/fbU/XnFxk3Paxubgxud/6TnUz23PnXjv4p2Jj4JV+OfPZw6PcHTj61jf3LFh7714/Szm0fOpoz+uHNrzQtmJqoe9m4LP+tEsZ+f5w2ddM6X12w6fWpv6Y3N7Yql7cPkjHySW1e27cHp324L9O6iRM4dcyr5ztcNvD647uOvD/d/t2/TXx9urXtrZMzu3aMaZ12qeO/Vi4sKi44nZjh0DxtbMOrZp28iyk7/9+YNv8JWtjy/4tf7oV5ktfPmeUfn+BhY5X/3gEQAA\"";
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