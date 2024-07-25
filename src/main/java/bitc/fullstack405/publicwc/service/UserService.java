package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.Users; // 경로 수정
import java.util.Optional;

public interface UserService {

//    게시글 등록 매서드
    public void writeWc(WcInfo wcInfo);

    Optional<Users> findByEmail(String email); // 이메일로 사용자 조회
    Optional<Users> findById(Long userId); // ID로 사용자 조회
    void saveUser(Users user); // 사용자 저장
    boolean checkPassword(Users user, String rawPassword); // 비밀번호 확인
    void deleteUser(Long userId); // 사용자 삭제
}