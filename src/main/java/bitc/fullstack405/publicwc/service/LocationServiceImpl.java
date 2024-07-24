//package bitc.fullstack405.publicwc.service;
//
//import jakarta.json.Json;
//import jakarta.json.JsonObject;
//import jakarta.json.JsonReader;
//import jakarta.ws.rs.client.Client;
//import jakarta.ws.rs.client.ClientBuilder;
//import jakarta.ws.rs.client.WebTarget;
//import jakarta.ws.rs.core.Response;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.io.StringReader;
//
//@Service // 이 클래스가 Spring의 서비스 컴포넌트임을 알리는 어노테이션입니다.
//public class LocationServiceImpl implements LocationService {
//
//    // application.properties 또는 application.yml 파일에서 카카오 API 키를 읽어옵니다.
//    @Value("${kakao.map.api.key}")
//    private String apiKey;
//
//    // 카카오 주소 변환 API의 URL을 상수로 정의
//    private static final String API_URL = "https://dapi.kakao.com/v2/local/geo/coord2address.json";
//
//    @Override
//    public String getAddress(double lat, double lon) {
//        // HTTP 클라이언트를 생성합니다. (API 호출을 위해 필요합니다.)
//        Client client = ClientBuilder.newClient();
//        // API 호출을 위한 URL을 설정합니다. (위도와 경도를 쿼리 파라미터로 추가합니다.)
//        WebTarget target = client.target(API_URL)
//                .queryParam("x", lon)
//                .queryParam("y", lat);
//
//        // API 요청을 보냅니다. 헤더에 카카오 API 키를 포함합니다.
//        Response response = target.request()
//                .header("Authorization", "KakaoAK " + apiKey)
//                .get();
//
//        // 응답 상태가 OK(200)인지 확인합니다.
//        if (response.getStatus() == HttpStatus.OK.value()) {
//            // 응답 본문을 문자열로 읽어옵니다.
//            String responseBody = response.readEntity(String.class);
//
//            // JSON-P를 사용하여 응답 본문을 JSON 객체로 변환합니다.
//            JsonReader jsonReader = Json.createReader(new StringReader(responseBody));
//            JsonObject jsonObject = jsonReader.readObject();
//
//            // JSON 응답에서 주소를 추출합니다.
//            JsonObject addressObject = jsonObject.getJsonArray("documents").getJsonObject(0);
//            JsonObject address = addressObject.getJsonObject("address");
//            String addressName = address.getString("address_name");
//
//            return addressName;
//        } else {
//            // 응답 상태가 OK가 아닌 경우 예외를 발생시킵니다.
//            throw new RuntimeException("Failed to fetch location");
//        }
//    }
//}
