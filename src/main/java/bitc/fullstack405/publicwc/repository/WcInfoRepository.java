package bitc.fullstack405.publicwc.repository;

import bitc.fullstack405.publicwc.entity.WcInfo; // WcInfo 엔티티 임포트
import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository 임포트
import org.springframework.stereotype.Repository; // Repository 임포트

import java.util.List;

@Repository // 이 클래스가 Spring Data JPA의 레포지토리임을 지정
public interface WcInfoRepository extends JpaRepository<WcInfo, Integer> {
    // 도로명 주소로 화장실을 검색하는 메소드
    List<WcInfo> findByAddr1ContainingOrAddr2Containing(String addr1, String addr2);
}
