package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.Best;
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.repository.BestRepository;
import bitc.fullstack405.publicwc.repository.UsersRepository;
import bitc.fullstack405.publicwc.repository.WcInfoRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BestService {

    @Autowired
    private BestRepository bestRepository;

    @Autowired
    private WcInfoRepository wcInfoRepository;

    @Autowired
    private UsersRepository usersRepository;

    public int getLikeCount(String userId, int wcId) {
        return bestRepository.getLikeCount(userId, wcId);
    }

    public int getHateCount(String userId, int wcId) {
        return bestRepository.getHateCount(userId, wcId);
    }

    public void likeCountUp(String userId, int wcId) {
        Best best = new Best();
        best.setBestUsers(usersRepository.findById(userId).get());
        best.setBestWc(wcInfoRepository.findById(wcId).get());
        best.setGood(1);

        bestRepository.save(best);
    }

    public void hateCountUp(String userId, int wcId) {
        Best best = new Best();
        best.setBestUsers(usersRepository.findById(userId).get());
        best.setBestWc(wcInfoRepository.findById(wcId).get());
        best.setGood(0);

        bestRepository.save(best);
    }
}
