package bitc.fullstack405.publicwc.controller;

// User 모델 클래스 임포트
import bitc.fullstack405.publicwc.model.User;
// UserService 임포트
import bitc.fullstack405.publicwc.service.UserService;
// 의존성 주입을 위한 어노테이션
import org.springframework.beans.factory.annotation.Autowired;
// 스프링 MVC의 컨트롤러를 정의
import org.springframework.stereotype.Controller;
// 뷰에 데이터를 전달하기 위한 모델
import org.springframework.ui.Model;
// GET 요청을 처리하기 위한 어노테이션
import org.springframework.web.bind.annotation.GetMapping;
// POST 요청을 처리하기 위한 어노테이션
import org.springframework.web.bind.annotation.PostMapping;
// 요청 파라미터를 처리하기 위한 어노테이션
import org.springframework.web.bind.annotation.RequestParam;
// 세션 속성을 관리하기 위한 어노테이션
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
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 로그인 폼 제출 처리
    @PostMapping("/login")
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
