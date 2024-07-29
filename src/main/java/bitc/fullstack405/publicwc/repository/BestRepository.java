package bitc.fullstack405.publicwc.repository;

import bitc.fullstack405.publicwc.entity.Best;
import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.entity.WcInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BestRepository extends JpaRepository<Best, Integer> {
    Optional<Best> findByBestUsersAndBestWc(Users user, WcInfo wcInfo);
}
