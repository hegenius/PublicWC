package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.entity.WcInfo;

import java.util.Optional;

public interface UserService {

    void writeWc(WcInfo wcInfo);

    Optional<Users> findById(String userId); // ID로 사용자 조회
    void saveUser(Users user); // 사용자 저장
    boolean checkPassword(Users user, String rawPassword); // 비밀번호 확인
    void deleteUser(String userId); // 사용자 삭제
}
