package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.repository.WcInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToiletServiceImpl implements ToiletService {

    @Autowired
    private WcInfoRepository wcInfoRepository;

    @Override
    public List<WcInfo> searchToiletsByAddress(String address) {
        return wcInfoRepository.findByAddr1Containing(address);
    }

    @Override
    public List<WcInfo> searchToiletsByLevel(int level) {
        switch (level) {
            case 1:
                return wcInfoRepository.findAllByLevel(1);
            case 2:
                return wcInfoRepository.findAllByLevel(2);
            case 3:
                return wcInfoRepository.findAllByLevel(3);
            default:
                return List.of(); // 잘못된 등급일 경우 빈 리스트 반환
        }
    }

    @Override
    public List<WcInfo> getAllToilets() {
        return wcInfoRepository.findAll(); // 모든 화장실 조회
    }

    @Override
    public WcInfo addWcInfo(WcInfo wcInfo) {
        return wcInfoRepository.save(wcInfo); // 새 화장실 정보 추가
    }

    @Override
    public WcInfo updateWcInfo(int id, WcInfo wcInfo) {
        if (wcInfoRepository.existsById(id)) {
            wcInfo.setId(id); // ID를 설정하여 업데이트 수행
            return wcInfoRepository.save(wcInfo);
        }
        return null; // ID가 존재하지 않으면 null 반환
    }

    @Override
    public WcInfo findWcInfoById(int id) {
        return wcInfoRepository.findById(id).orElse(null); // ID로 화장실 정보 조회
    }

    @Override
    public boolean deleteWcInfo(int id) {
        if (wcInfoRepository.existsById(id)) {
            wcInfoRepository.deleteById(id); // ID로 화장실 정보 삭제
            return true;
        }
        return false; // ID가 존재하지 않으면 false 반환
    }

    @Override
    public List<WcInfo> parsingWc() {
        return wcInfoRepository.pointWc(); // 특정 조건의 화장실 정보 조회
    }
}
