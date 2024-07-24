package bitc.fullstack405.publicwc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        // 세션에서 사용자 정보를 가져옴
        Object user = session.getAttribute("user");

        // 로그인 상태에 따라 모델에 추가
        model.addAttribute("isLoggedIn", user != null);
        if (user != null) {
            model.addAttribute("username", user); // 로그인한 사용자 이름을 모델에 추가
        }

        return "index"; // 타임리프 템플릿 파일 이름
    }
}
