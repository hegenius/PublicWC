package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Users;
//import bitc.fullstack405.publicwc.model.User;
import bitc.fullstack405.publicwc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 회원 가입 페이지 요청 처리
    @GetMapping("/signup")
    public String showSignupPage() {
        return "/login/signUp";
    }

    // 회원 가입 폼 제출 처리
    @PostMapping("/signup")
    public ModelAndView signup(@RequestParam String userId,
                               @RequestParam String password,
//                               @RequestParam String email,
                               @RequestParam String confirmPassword,
                               @RequestParam String gender,
                               @RequestParam String handicap
    ) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/main");
        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (!password.equals(confirmPassword)) {
            mv.addObject("error", "비밀번호가 일치하지 않습니다.");
            return mv;
        }

        boolean changeGender = (gender.equals("man") ? true : false);

        boolean changeHandicap = false;

        if(handicap.equals("ok")) {
            changeHandicap = true;
        }
        else if(handicap.equals("") || handicap == null) {
            changeHandicap = false;
        }
//        boolean changeHandicap = (handicap.equals("ok") ? true : false);

        // 새로운 사용자 객체 생성
        Users newUser = new Users();
        newUser.setId(userId);
        newUser.setPassword(password); // 비밀번호는 암호화됨
        newUser.setEmail("123@gmail.com");
        newUser.setGender(changeGender);
        newUser.setHandicap(changeHandicap);

        // 사용자 정보를 데이터베이스에 저장
        userService.saveUser(newUser);
//        model.addAttribute("message", "회원 가입이 완료되었습니다. 로그인을 해주세요.");
        return mv;
    }

//    // 마이페이지 요청 처리
//    @GetMapping("/mypage")
//    public String showMyPage(@SessionAttribute("userId") Long userId, Model model) {
//        Optional<User> userOptional = userService.findById(userId);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            model.addAttribute("user", user);
//        } else {
//            model.addAttribute("errorMessage", "사용자 정보를 찾을 수 없습니다.");
//        }
//
//        return "mypage";
//    }

//    // 마이페이지 정보 수정 처리
//    @PostMapping("/mypage/update")
//    public String updateUserInfo(@SessionAttribute("userId") Long userId,
//                                 @RequestParam String email,
//                                 @RequestParam String gender,
//                                 @RequestParam boolean isDisabled,
//                                 Model model) {
//        Optional<User> userOptional = userService.findById(userId);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            user.setEmail(email);
//            user.setGender(gender);
//            user.setDisabled(isDisabled);
//
//            userService.saveUser(user);
//
//            model.addAttribute("message", "정보가 성공적으로 수정되었습니다.");
//        } else {
//            model.addAttribute("errorMessage", "사용자 정보를 수정할 수 없습니다.");
//        }
//
//        return "/login/signUp";
//    }
//
//    // 회원 탈퇴 처리
//    @PostMapping("/mypage/delete")
//    public String deleteUser(@SessionAttribute("userId") Long userId, Model model) {
//        Optional<User> userOptional = userService.findById(userId);
//
//        if (userOptional.isPresent()) {
//            userService.deleteUser(userId);
//            model.addAttribute("message", "회원 탈퇴가 완료되었습니다.");
//        } else {
//            model.addAttribute("errorMessage", "사용자 정보를 찾을 수 없습니다.");
//        }
//
//        return "redirect:/login/login";
//    }
}