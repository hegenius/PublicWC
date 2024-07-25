package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.service.JusoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bitc.fullstack405.publicwc.service.LocationService;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api") // API 경로를 통일하기 위한 기본 경로 설정
public class LocationController {

    private final LocationService locationService;

    @Autowired
    JusoService jusoService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/get-location") // GET 메서드로 변경
    public ResponseEntity<String> getLocation(@RequestParam double lat, @RequestParam double lon) {
        try {
            String address = locationService.getAddress(lat, lon);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("주소를 가져오는데 실패했습니다: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/address")
    public String getAddress(@RequestParam String juso) {
        return jusoService.getAddress(juso);
    }

    @GetMapping("/search.do")
    public ModelAndView searchMap(@RequestParam String juso) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("jusoValue", juso);
        mv.setViewName("/board/KakaoMapView2");
        return mv;
    }
    ;
}
