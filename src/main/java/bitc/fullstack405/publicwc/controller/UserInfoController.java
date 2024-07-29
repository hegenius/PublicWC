package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @GetMapping("/mypage")
    public String showMyPage(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/auth/login";
        }

        Optional<Users> userOptional = userService.findById(userId);

        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
        } else {
            model.addAttribute("error", "사용자를 찾을 수 없습니다.");
        }
        return "login/myPage";
    }

    @PostMapping("/mypage")
    public String updateMyPage(@RequestParam String password,
                               @RequestParam String password2,
                               @RequestParam String gender,
                               @RequestParam String handicap,
                               HttpSession session,
                               Model model) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/auth/login";
        }

        // 비밀번호와 비밀번호 확인 필드가 일치하지 않을 때 오류 처리
        if (!password.equals(password2)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            // 현재 페이지로 리다이렉트하여 오류 메시지를 표시
//            return "users/myPage";
            return "redirect:/users/mypage";
        }

        Optional<Users> userOptional = userService.findById(userId);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setPassword(password); // 비밀번호 암호화가 필요 없음
            user.setGender(gender);
            user.setHandicap(handicap);
            userService.saveUser(user);
            model.addAttribute("user", user);
            model.addAttribute("success", "정보가 성공적으로 수정되었습니다.");
        } else {
            model.addAttribute("error", "사용자를 찾을 수 없습니다.");
        }
        return "login/myPage";
    }
}
