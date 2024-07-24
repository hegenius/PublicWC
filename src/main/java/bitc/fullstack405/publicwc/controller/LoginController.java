package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.model.User;
import bitc.fullstack405.publicwc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
// 세션에서 "user" 속성을 관리
@SessionAttributes("user")
public class LoginController {

    @Autowired
    private UserService userService;

    // 메인 페이지 요청 처리
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 로그인 페이지 요청 처리
    @GetMapping("/board2/login")
    public String login() {
        return "login";
    }

    // 로그인 폼 제출 처리
    @PostMapping("/board2/login/loginProcess.do")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // 입력된 사용자 이름으로 사용자 정보를 조회
        User user = userService.findByUsername(username).orElse(null);

        // 사용자 정보가 존재하고 비밀번호가 일치하는지 확인
        if (user != null && userService.checkPassword(user, password)) {
            model.addAttribute("user", user);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}