package bitc.fullstack405.publicwc.repository;

import bitc.fullstack405.publicwc.entity.Best;
import bitc.fullstack405.publicwc.entity.Favorite;
import bitc.fullstack405.publicwc.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

//    @Query("SELECT fa FROM Favorite fa WHERE fa.favoriteUsers == :user")
//    Favorite findByUser(@Param("user")Users users);

}
