package bitc.fullstack405.publicwc.repository;


import bitc.fullstack405.publicwc.entity.Best;
import bitc.fullstack405.publicwc.entity.WcInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BestRepository extends JpaRepository<Best, Integer> {

    @Query("select count(*) as cnt from Best b where b.bestWc.id = :wcId and b.good = 1")
    int getLikeCount(int wcId);

    @Query("select count(*) as cnt from Best b where b.bestWc.id = :wcId and b.good = 0")
    int getHateCount(int wcId);

//    @Query("")
//    void setLikeCountUp(String userId, int wcId);
}
