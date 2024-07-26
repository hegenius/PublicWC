package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @GetMapping("/mypage")
    public String showMyPage(@SessionAttribute("userId") String userId, Model model) {
        Optional<Users> userOptional = userService.findById(userId);

        userOptional.ifPresentOrElse(
                user -> model.addAttribute("user", user),
                () -> model.addAttribute("errorMessage", "사용자 정보를 찾을 수 없습니다.")
        );

        return "mypage";
    }

    @PostMapping("/mypage/update")
    public String updateUserInfo(@SessionAttribute("userId") String userId,
                                 @RequestParam String email,
                                 @RequestParam String gender,
                                 @RequestParam String handicap,
                                 Model model) {
        Optional<Users> userOptional = userService.findById(userId);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setEmail(email);
            user.setGender(gender);
            user.setHandicap(handicap);

            userService.saveUser(user);
            model.addAttribute("message", "정보가 성공적으로 수정되었습니다.");
        } else {
            model.addAttribute("errorMessage", "사용자 정보를 수정할 수 없습니다.");
        }

        return "mypage";
    }

    @PostMapping("/mypage/delete")
    public String deleteUser(@SessionAttribute("userId") String userId, Model model) {
        Optional<Users> userOptional = userService.findById(userId);

        if (userOptional.isPresent()) {
            userService.deleteUser(userId);
            model.addAttribute("message", "회원 탈퇴가 완료되었습니다.");
            return "redirect:/auth/login"; // 탈퇴 후 로그인 페이지로 리다이렉트
        } else {
            model.addAttribute("errorMessage", "사용자 정보를 찾을 수 없습니다.");
            return "mypage";
        }
    }
}
