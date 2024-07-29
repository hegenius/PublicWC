package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.dto.WcInfoWithBestDTO;
import bitc.fullstack405.publicwc.entity.Best;
import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.repository.BestRepository;
import bitc.fullstack405.publicwc.repository.UsersRepository;
import bitc.fullstack405.publicwc.repository.WcInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WcInfoBestService {

//    상세글 좋아요 싫어요 레파제토리에서 데이터 가져오는 서비스 클래스

    @Autowired
    private WcInfoRepository wcInfoRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BestRepository bestRepository;

    public WcInfoWithBestDTO getWcInfoWithBestDetails(int wcId) {
        return wcInfoRepository.findWcInfoWithBestDetails(wcId);
    }

    public void addBest(int wcId, String userId, boolean isLike) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")); // 사용자 조회
        WcInfo wcInfo = wcInfoRepository.findById(wcId)
                .orElseThrow(() -> new RuntimeException("WcInfo not found"));

        Optional<Best> bestOptional = bestRepository.findByBestUsersAndBestWc(user, wcInfo);

        Best best;
        if (bestOptional.isPresent()) {
            best = bestOptional.get();
            best.setGood(isLike);
        }
        else {
            best = new Best();
            best.setBestUsers(user);
            best.setBestWc(wcInfo);
            best.setGood(isLike);
        }

        bestRepository.save(best);
    }

    public String getBestStatus(int wcId, String userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        WcInfo wcInfo = wcInfoRepository.findById(wcId)
                .orElseThrow(() -> new RuntimeException("WcInfo not found"));

        Optional<Best> bestOptional = bestRepository.findByBestUsersAndBestWc(user, wcInfo);

        if (bestOptional.isPresent()) {
            return bestOptional.get().isGood() ? "like" : "dislike";
        } else {
            return "none";
        }
    }
}
