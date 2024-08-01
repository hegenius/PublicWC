package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.service.UserService;
import bitc.fullstack405.publicwc.service.ToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @Autowired
    private ToiletService toiletService;

    // 메인 페이지 요청 처리
    @GetMapping("/home")
    public String home() {
        return "index";
    }

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
    public ResponseEntity<?> updateUser(@RequestParam(required = false) String password,
                                        @RequestParam(required = false) String password2,
                                        @RequestParam String gender,
                                        @RequestParam String handicap,
                                        HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(403).body(Map.of("error", "로그인 후 접근 가능합니다."));
        }

        Optional<Users> userOptional = userService.findById(userId);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            if (password != null && !password.isEmpty()) {
                // 비밀번호 길이 확인
                if (password.length() < 4 || password2.length() < 4) {
                    return ResponseEntity.badRequest().body(Map.of("error", "비밀번호는 최소 4자 이상이어야 합니다."));
                }

                // 비밀번호와 비밀번호 확인 필드가 일치하지 않을 때 오류 처리
                if (!password.equals(password2)) {
                    return ResponseEntity.badRequest().body(Map.of("error", "비밀번호가 일치하지 않습니다."));
                }

                // 비밀번호가 기존 비밀번호와 동일한지 확인
                if (user.getPassword().equals(password)) {
                    return ResponseEntity.badRequest().body(Map.of("error", "새 비밀번호는 기존 비밀번호와 달라야 합니다."));
                }

                user.setPassword(password); // 비밀번호 암호화는 필요 없음 (비즈니스 로직에 따라)
            }

            user.setGender(gender);
            user.setHandicap(handicap);
            userService.saveUser(user);

            return ResponseEntity.ok(Map.of("success", "회원 정보가 성공적으로 수정되었습니다."));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "사용자를 찾을 수 없습니다."));
        }
    }

    @GetMapping("/passkey")
    @ResponseBody
    public Integer getPasskey(HttpSession session) {
        Object userId = session.getAttribute("userId");
        if (userId != null) {
            Optional<Users> userOptional = userService.findById(userId.toString());
            if (userOptional.isPresent()) {
                Users user = userOptional.get();
                int passkey = user.getPasskey();
                return passkey;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @PostMapping("/usePasskey")
    @ResponseBody
    public ResponseEntity<?> usePasskey(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            toiletService.usePasskey(userId);
            Optional<Users> userOptional = userService.findById(userId);
            if (userOptional.isPresent()) {
                Users user = userOptional.get();
                return ResponseEntity.ok().body(Map.of("passkey", user.getPasskey()));
            } else {
                return ResponseEntity.status(404).body("User not found");
            }
        } else {
            return ResponseEntity.status(403).body("Not logged in");
        }
    }
}
