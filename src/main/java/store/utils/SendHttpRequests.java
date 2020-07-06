package store.utils;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("ALL")
@Component
public class SendHttpRequests<T> {
    static final String token ="\"v^1.1#i^1#p^1#I^3#f^0#r^0#t^H4sIAAAAAAAAAOVYa2wUVRTudrelBZGQGCE8wjpITDAze2dmZ+chu8nSgqyWtrClpQ2kmceddmB2Zpm5S7v8oTZK8EEUQwmCYhNMNCKJpPJoAtgfGoVgQCHGiIaqGAgaX4nWAD5mpkvZVuTVNTRx/2zm3HPP/c73nXPvnQEdpeVzNyzaMDDRN664uwN0FPt85ARQXlry8L3+4mklRSDPwdfd8WBHoNN/YZ4tpvS0sBTaadOwYbA9pRu24BmjWMYyBFO0NVswxBS0BSQLyfjiKoEigJC2TGTKpo4FE5VRLMLwEVVRGJVj1DDLi47VuBqzzoxiJKWILFRkFSgkLcruuG1nYMKwkWigKEYBCuCAxUGkjmQFmheYMMEBugkL1kPL1kzDcSEAFvPgCt5cKw/rjaGKtg0t5ATBYon4wmRNPFG5oLpuXigvVizHQxKJKGMPf6owFRisF/UMvPEytuctJDOyDG0bC8UGVxgeVIhfBXMH8D2qmbBKSrLMMhEeqpwECkLlQtNKiejGOFyLpuCq5ypAA2koezNGHTakVVBGuadqJ0SiMuj+LcmIuqZq0IpiC+bHG+O1tVgMSmKW5ThSxCWSlvCwqIRxkZYiOBeGNBdhWEBLuUUGI+UoHrFKhWkomkuYHaw20XzoIIYjeaHyeHGcaowaK64iF02+H5/jj+X5JlfQQQUzqNVwNYUph4Sg93hz9odmI2RpUgbBoQgjBzx6opiYTmsKNnLQq8Nc6bTbUawVobQQCrW1tRFtNGFaLSEKADK0fHFVUm6FKafRBn3dXm+3tZtPwDUvFRk6M21NQNm0g6XdqVMHgNGCxUgqzFJ0jvfhsGIjrf8w5OUcGt4NheoOqFCkEoEyz9IK4CimEN0RyxVoyMXh1iieEq3VEKV1UYa47NRZJgUtTRFoRqVoToW4EuFVPMyrKi4xSgQnVQgBhJIk89z/pUlutcyTULYgKlidF6TGmUyk2hE7TrWEScS1cdkGi0uubVha0dSwdAm9jny0LttEodY1YcBFb7UTrp+8bKZhralrcraADLi9XgAWaEupFS2UTUJddwyjStR2Ex1bIrvzbSeAmNYIt6cI2UyFTNHZzV1Ts4d4VDnH0+lEKpVBoqTDROF28ruwi183Pc2544ypnBz9BoXUlMHLCeGpSdhrZcKCtpmxnHsZUeOe13Xmamg4OyCyTF2HVj05aqHvlr5ur/8LH7dxUNxZ3oW9oYyVupZ1zSmf5rGW2X+upiaOsVOYZPgwYFiSD48qrwpPz7rsWDt/Fpk2gsrtXKQD7C1epUPDX+pjRd6P7PTtA52+vcU+HwiBOeRs8ECpf1nAf880W0OQ0ESVsLUWw3lXtSCxGmbTomYVl/rSy8SLc/I+I3SvBFOHPiSU+8kJeV8VwIxrIyXkpCkTHUpYECFZmmfCTWD2tdEAeX/gvotHtsLQFWX747PevjLp2+z2d05/MBlMHHLy+UqKAp2+otSX4zeVHckcffN3BVx57uyeJV/3ibPfm9r347p32af3blrfBdYsXnGgseaVRy5fuDC3qnlc9699pVN3TaN68JMPbfPVn+fLmteX9TT27/oQ9h3r7/3lraOHezM/9cKt1Z9LG/ftfP6lzv5Dn07u+eyP08/2j98dev/EseN/rWiONsqNRw//tuhSy4sD2/Yf+Obl6bvPHRReHZjcMGvmurObv9vVfyk1UPH6wZnZ8dX+jpOPGZEtZzZuWUvg3yc2XdzRtbxKr+jZeern6Z1frdrzwtkFM1ae2bgwfmpz8sEvOH/wh8qPn+o917wDnH+jpvbciSnlzyx/7ZM+6U/m+EctgVlrDnWV1M4tu/xE2/4nT3QNyvc3LGAOo+ARAAA=\"";
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