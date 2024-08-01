package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Favorite;
import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/location") // API 경로를 통일하기 위한 기본 경로 설정
public class LocationController {

    @Autowired
    ToiletService toiletService;

    @Autowired
    BestService bestService;

    @Autowired
    FavoriteService favoriteService;

    @GetMapping("/search.do")
    public ModelAndView searchMap(@RequestParam String juso) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("jusoValue", juso);
        mv.setViewName("/board/boardList");

        return mv;
    }

    @PostMapping("/wcInfoList")
    @ResponseBody
    public Object getWcInfoList(String juso) {
        List<WcInfo> wcInfoList = toiletService.parsingWc(juso);

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
