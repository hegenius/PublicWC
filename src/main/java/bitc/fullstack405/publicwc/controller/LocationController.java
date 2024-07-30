package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.service.JusoService;
import bitc.fullstack405.publicwc.service.ToiletService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/location") // API 경로를 통일하기 위한 기본 경로 설정
public class LocationController {

//    private final LocationController locationService;

//    @Autowired
//    JusoService jusoService;

    @Autowired
    ToiletService toiletService;

//    @Autowired
//    public LocationController(LocationService locationService) {
//        this.locationService = locationService;
//    }
//
//    @GetMapping("/get-location") // GET 메서드로 변경
//    public ResponseEntity<String> getLocation(@RequestParam double lat, @RequestParam double lon) {
//        try {
//            String address = locationService.(lat, lon);
//            return new ResponseEntity<>(address, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>("주소를 가져오는데 실패했습니다: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/address")
//    public String getAddress(@RequestParam String juso) {
//        return jusoService.getAddress(juso);
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

    @GetMapping("/search.do")
    public ModelAndView searchMap(@RequestParam String juso) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("jusoValue", juso);
        mv.setViewName("/board/boardList");

        return mv;
    }

    @PostMapping("/wcInfoList")
    @ResponseBody
    public Object getWcInfoList() {
        List<WcInfo> wcInfoList = toiletService.parsingWc();

        return wcInfoList;
    }

    @GetMapping("/wcDetail")
    public ModelAndView wcDetail(@RequestParam("wcId") String wcId, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("board/boardDetail");

        int wcIntId = Integer.parseInt(wcId);
        WcInfo wcInfo = toiletService.findWcInfoById(wcIntId);

        mv.addObject("userId", session.getAttribute("userId"));
        mv.addObject("wcId", wcId);
        mv.addObject("wcInfo", wcInfo);


        return mv;
    }

}
