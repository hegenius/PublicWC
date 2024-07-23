package bitc.fullstack405.publicwc.repository;

import bitc.fullstack405.publicwc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// User 엔티티에 대한 데이터베이스 접근을 위한 인터페이스
public interface UserRepository extends JpaRepository<User, Long> {

    // 사용자 이름으로 User 객체를 검색하는 메서드
    User findByUsername(String username);

    // 사용자 이름이 존재하는지 여부를 확인하는 메서드
    boolean existsByUsername(String username);
}
