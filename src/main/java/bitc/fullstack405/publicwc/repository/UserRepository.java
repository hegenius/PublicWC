package bitc.fullstack405.publicwc.repository;

import bitc.fullstack405.publicwc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
