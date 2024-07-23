package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.model.User;
import bitc.fullstack405.publicwc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // BCryptPasswordEncoder 객체 생성
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 사용자 이름으로 User 객체를 검색하는 메서드
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    // 사용자 ID로 User 객체를 검색하는 메서드
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    // User 객체를 저장하는 메서드
    public void saveUser(User user) {
        // 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // 비밀번호를 확인하는 메서드
    public boolean checkPassword(User user, String rawPassword) {
        // 암호화된 비밀번호와 입력된 비밀번호 비교
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    // 회원 탈퇴 메서드
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}