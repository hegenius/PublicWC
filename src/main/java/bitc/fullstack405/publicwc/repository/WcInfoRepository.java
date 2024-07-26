package bitc.fullstack405.publicwc.repository;

import bitc.fullstack405.publicwc.dto.WcInfoWithBestDTO;
import bitc.fullstack405.publicwc.entity.WcInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface WcInfoRepository extends JpaRepository<WcInfo, Integer> {

//    @Query(
//            "SELECT wi.time, wi.addr1, wi.wcpass, wi.latitude, wi.longitude " +
//                    "FROM WcInfo wi WHERE wi.id = :id")
//    List<Object[]> wcinfoList(@Param("id") Integer id); // 화장실 목록 리스트


//    주소기반 전체 컬럼 목록 리스트
    @Query("SELECT wi FROM WcInfo wi WHERE wi.addr1 LIKE %:addr1%")
    List<WcInfo> wcList(@Param("addr1") String addr1);

//    1등급인 모든 화장실 리스트
    @Query("SELECT wi FROM WcInfo wi WHERE wi.level = 1")
    List<WcInfo> wcList1();

//    2등급인 모든 화장실 리스트
    @Query("SELECT wi FROM WcInfo wi WHERE wi.level = 2")
    List<WcInfo> wcList2();

//    3등급인 모든 화장실 리스트
    @Query("SELECT wi FROM WcInfo wi WHERE wi.level = 3")
    List<WcInfo> wcList3();

//    서면역 근처 화장실들 포인트 찝어놓은거
    @Query("SELECT wi FROM WcInfo wi WHERE wi.point = 'a'")
    List<WcInfo> pointWc();

//    게시글 상세보기 좋아요 싫어요 구현
    @Query("SELECT new bitc.fullstack405.publicwc.dto.WcInfoWithBestDTO(" +
            "wi.id, wi.level, wi.name, wi.addr1, wi.detailAddr, wi.time, wi.comment, wi.wcpass, " +
            "SUM(CASE WHEN b.good = true THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN b.good = false THEN 1 ELSE 0 END)) " +
            "FROM WcInfo wi " +
            "LEFT JOIN Best b ON wi.id = b.bestWc.id " +
            "WHERE wi.id = :wcId " +
            "GROUP BY wi.id, wi.level, wi.name, wi.addr1, wi.detailAddr, wi.time, wi.comment, wi.wcpass")
    WcInfoWithBestDTO findWcInfoWithBestDetails(@Param("wcId") int wcId);
}
