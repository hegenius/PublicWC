package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.Users; // 경로 수정
import bitc.fullstack405.publicwc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Users> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email)); // 이메일로 사용자 조회
    }

    @Override
    public Optional<Users> findById(Long userId) {
        return userRepository.findById(userId); // ID로 사용자 조회
    }

    @Override
    public void saveUser(Users user) {
        // 비밀번호 암호화 없이 저장 (단순히 사용자 정보 저장)
        userRepository.save(user);
    }

    @Override
    public boolean checkPassword(Users user, String rawPassword) {
        // 비밀번호 확인 로직을 제거했습니다.
        // 비밀번호 확인 기능이 필요하다면 별도의 로직을 추가해야 합니다.
        return user.getPassword().equals(rawPassword); // 비밀번호 비교 (단순 비교)
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId); // 사용자 삭제
    }
}