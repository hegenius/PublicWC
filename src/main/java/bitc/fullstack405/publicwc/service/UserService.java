package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.Users;
//import bitc.fullstack405.publicwc.model.User;
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.repository.UsersRepository;
import bitc.fullstack405.publicwc.repository.WcInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private WcInfoRepository wcInfoRepository;

    // BCryptPasswordEncoder 객체 생성
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 사용자 이름으로 User 객체를 검색하는 메서드
//    public Optional<Users> findByUsername(String username) {
//        return Optional.ofNullable(userRepository.findByUsername(username));
//    }

    // 사용자 ID로 User 객체를 검색하는 메서드
//    public Optional<Users> findById(Long userId) {
//        return userRepository.findById(userId);
//    }

//    회원가입하는 매서드
    public void saveUser(Users user) {

        userRepository.save(user);
    }

//    게시글 등록 매서드
    public void writeWc(WcInfo wcInfo) {

        wcInfoRepository.save(wcInfo);
    }



    // 비밀번호를 확인하는 메서드
//    public boolean checkPassword(Users user, String rawPassword) {
//        // 암호화된 비밀번호와 입력된 비밀번호 비교
//        return passwordEncoder.matches(rawPassword, user.getPassword());
//    }

    // 회원 탈퇴 메서드
//    public void deleteUser(Long userId) {
//        userRepository.deleteById(userId);
//    }
}