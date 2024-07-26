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
        return wcInfoRepository.findByAddressContaining(address);
    }

    @Override
    public List<WcInfo> searchToiletsByLevel(int level) {
        switch (level) {
            case 1:
                return wcInfoRepository.findAllLevel1();
            case 2:
                return wcInfoRepository.findAllLevel2();
            case 3:
                return wcInfoRepository.findAllLevel3();
            default:
                return List.of(); // 잘못된 등급일 경우 빈 리스트
        }
    }

    @Override
    public List<WcInfo> getAllToilets() {
        return wcInfoRepository.findAll(); // 모든 화장실 조회
    }

    @Override
    public WcInfo addWcInfo(WcInfo wcInfo) {
        return wcInfoRepository.save(wcInfo);
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
    public void deleteWcInfo(int id) {
        wcInfoRepository.deleteById(id);
    }
}
