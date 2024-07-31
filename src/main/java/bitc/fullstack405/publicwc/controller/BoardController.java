package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.service.ToiletService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private ToiletService toiletService;

    @GetMapping("/write")
    public String boardWrite(Model model) {
        return "board/boardWrite"; // boardWrite.html을 렌더링
    }


    @PostMapping("/write")
    public String submitPost(@ModelAttribute WcInfo wcinfo, HttpSession session) {

        if (session.getAttribute("userId") != null) {
            wcinfo.setCreateUserId(session.getAttribute("userId").toString());

            toiletService.addWcInfo(wcinfo);

            return "redirect:/location/search.do?jusoValue=";
        } else {
            return "redirect:login/login";
        }
    }

    @GetMapping("/list")
    public String boardList(Model model) {
        // 모든 화장실 정보를 가져와서 모델에 추가
        model.addAttribute("wcList", toiletService.getAllToilets());
        return "board/boardList"; // boardList.html을 렌더링
    }
}

