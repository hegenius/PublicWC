package bitc.fullstack405.publicwc.repository;

import bitc.fullstack405.publicwc.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, String> {



//    @Modifying
//    @Query("INSERT INTO users (id, password, email, handicap, gender, pass_key) " +
//            "VALUES (:id, :password, :email, :handicap, :gender, :pass_key)")
//
//    void signup(@Param("id") String id,
//                 @Param("password") String password,
//                 @Param("email") String email,
//                 @Param("handicap") boolean handicap,
//                 @Param("gender") boolean gender,
//                 @Param("pass_key") int pass_key);
}
