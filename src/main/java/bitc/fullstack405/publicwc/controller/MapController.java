package bitc.fullstack405.publicwc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @GetMapping("/search.do")
    public String searchMap() {
        return "board/KakaoMapView";
    }

}
