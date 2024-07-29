package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserInfoController {

    @Autowired
    private UserService userService;

    // 메인 페이지 요청 처리
    @GetMapping("/")
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
            return "user/mypage"; // 임시 페이지로 리다이렉트
        } else {
            return "redirect:/auth/login";
        }
    }

    // 특정 사용자의 즐겨찾기 리스트에 화장실을 추가하는 엔드포인트
//    @PostMapping("/{userId}/favorites/{wcId}")
//    public ResponseEntity<String> addFavorite(@PathVariable String userId, @PathVariable int wcId) {
//        try {
//            userService.addFavorite(userId, wcId); // UserService를 통해 즐겨찾기 추가
//            return ResponseEntity.ok("Favorite added successfully"); // 성공 메시지 반환
//            } catch (RuntimeException e) {
//                return ResponseEntity.badRequest().body(e.getMessage());
//            }
//        }

//    @GetMapping("/{userId}/favorites/{wcId}")
//    public ResponseEntity<Boolean> isFavorite(@PathVariable String userId, @PathVariable int wcId) {
//        Optional<Users> userOptional = userService.findById(userId);
//        if (userOptional.isPresent()) {
//            Users user = userOptional.get();
//            boolean isFavorite = user.getFavoriteWcList().stream()
//                    .anyMatch(wc -> wc.getId() == wcId);
//            return ResponseEntity.ok(isFavorite);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
