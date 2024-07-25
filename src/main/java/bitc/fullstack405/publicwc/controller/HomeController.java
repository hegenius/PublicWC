package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        // 세션에서 사용자 정보를 가져옴
        Users user = (Users) session.getAttribute("users"); // Object에서 Users로 변경

        // 로그인 상태에 따라 모델에 추가
        model.addAttribute("isLoggedIn", user != null);
        if (user != null) {
            // 로그인한 사용자의 이메일을 모델에 추가
            model.addAttribute("email", user.getEmail()); // username을 email로 변경
        }

        return "index"; // 타임리프 템플릿 파일 이름
    }
}
