package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.service.ToiletService;
import bitc.fullstack405.publicwc.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private ToiletService toiletService;

    @Autowired
    private UserService userService;

    @GetMapping("/write")
    public String boardWrite(Model model) {
        return "board/boardWrite"; // boardWrite.html을 렌더링
    }

    @PostMapping("/write")
    public String submitPost(@ModelAttribute WcInfo wcinfo, HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            wcinfo.setCreateUserId(userId);
            toiletService.addWcInfo(wcinfo);

            // 사용자 정보 업데이트
            Optional<Users> userOptional = userService.findById(userId);
            if (userOptional.isPresent()) {
                Users user = userOptional.get();
                user.setPasskey(user.getPasskey() + 3);
                userService.saveUser(user);
                session.setAttribute("passkey", user.getPasskey());
            } else {
                model.addAttribute("error", "사용자 정보를 찾을 수 없습니다.");
                return "board/boardWrite";
            }

            String addr1 = wcinfo.getAddr1();
            String encodedAddr1 = "";
            try {
                encodedAddr1 = URLEncoder.encode(addr1, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return "redirect:/location/search.do?juso=" + encodedAddr1;
        } else {
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/list")
    public String boardList(Model model) {
        // 모든 화장실 정보를 가져와서 모델에 추가
        model.addAttribute("wcList", toiletService.getAllToilets());
        return "board/boardList"; // boardList.html을 렌더링
    }
}
