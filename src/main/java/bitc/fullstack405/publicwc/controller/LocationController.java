//package bitc.fullstack405.publicwc.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import bitc.fullstack405.publicwc.service.LocationService;
//
//@RestController
//@RequestMapping("/api") // API 경로를 통일하기 위한 기본 경로 설정
//public class LocationController {
//
//    private final LocationService locationService;
//
//    @Autowired
//    public LocationController(LocationService locationService) {
//        this.locationService = locationService;
//    }
//
//    @GetMapping("/get-location") // GET 메서드로 위치 정보를 가져옴
//    public ResponseEntity<String> getLocation(@RequestParam double lat, @RequestParam double lon) {
//        try {
//            // 위도와 경도로 주소를 가져옴
//            String address = locationService.getAddress(lat, lon);
//            return new ResponseEntity<>(address, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            // 주소를 가져오지 못한 경우 에러 메시지 반환
//            return new ResponseEntity<>("주소를 가져오는데 실패했습니다: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
