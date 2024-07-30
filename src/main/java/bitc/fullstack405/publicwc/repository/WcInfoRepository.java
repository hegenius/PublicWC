package bitc.fullstack405.publicwc.repository;

import bitc.fullstack405.publicwc.dto.WcInfoWithBestDTO;
import bitc.fullstack405.publicwc.entity.WcInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WcInfoRepository extends JpaRepository<WcInfo, Integer> {

    // 주소를 포함하는 모든 화장실 리스트 조회
    @Query("SELECT wi FROM WcInfo wi WHERE wi.addr1 LIKE %:addr1%")
    List<WcInfo> findByAddressContaining(@Param("addr1") String addr1);

    // 특정 등급의 모든 화장실 리스트 조회
    @Query("SELECT wi FROM WcInfo wi WHERE wi.level = :level")
    List<WcInfo> findByLevel(@Param("level") int level);

    // 등급이 1인 모든 화장실 리스트 조회
    @Query("SELECT wi FROM WcInfo wi WHERE wi.level = 1")
    List<WcInfo> findAllLevel1();

    // 등급이 2인 모든 화장실 리스트 조회
    @Query("SELECT wi FROM WcInfo wi WHERE wi.level = 2")
    List<WcInfo> findAllLevel2();

    // 등급이 3인 모든 화장실 리스트 조회
    @Query("SELECT wi FROM WcInfo wi WHERE wi.level = 3")
    List<WcInfo> findAllLevel3();

    // 포인트가 '내위치'인 화장실 리스트 조회
    @Query("SELECT wi FROM WcInfo wi WHERE wi.point = '내위치'")
    List<WcInfo> pointWc();

    // 게시글 상세보기 좋아요 싫어요 구현
    @Query("SELECT new bitc.fullstack405.publicwc.dto.WcInfoWithBestDTO(" +
            "wi.id, wi.level, wi.name, wi.addr1, wi.detailAddr, wi.time, wi.comment, wi.wcpass, " +
            "SUM(CASE WHEN b.good = true THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN b.good = false THEN 1 ELSE 0 END)) " +
            "FROM WcInfo wi " +
            "LEFT JOIN Best b ON wi.id = b.bestWc.id " +
            "WHERE wi.id = :wcId " +
            "GROUP BY wi.id, wi.level, wi.name, wi.addr1, wi.detailAddr, wi.time, wi.comment, wi.wcpass")
    WcInfoWithBestDTO findWcInfoWithBestDetails(@Param("wcId") int wcId);

    // 회원이 찜한 즐겨찾기 리스트 조회
    @Query("SELECT wi FROM WcInfo wi WHERE wi.id IN (SELECT f.favoriteWc.id FROM Favorite f WHERE f.favoriteUsers.id = :userId)")
    List<WcInfo> findFavoriteWcListsByUserId(@Param("userId") String userId);

    List<WcInfo> findAllByLevel(int i);

    List<WcInfo> findByAddr1Containing(String address);
}

