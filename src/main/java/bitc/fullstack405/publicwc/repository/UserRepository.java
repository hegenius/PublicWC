package bitc.fullstack405.publicwc.repository;

import bitc.fullstack405.publicwc.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    boolean existsByUsername(String username);

    Users findByEmail(String email);
}