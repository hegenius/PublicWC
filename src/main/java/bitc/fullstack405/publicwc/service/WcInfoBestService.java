package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.dto.WcInfoWithBestDTO;
import bitc.fullstack405.publicwc.repository.WcInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WcInfoBestService {

//    상세글 좋아요 싫어요 레파제토리에서 데이터 가져오는 서비스 클래스

    @Autowired
    private WcInfoRepository wcInfoRepository;

    public WcInfoWithBestDTO getWcInfoWithBestDetails(int wcId) {
        return wcInfoRepository.findWcInfoWithBestDetails(wcId);
    }
}
