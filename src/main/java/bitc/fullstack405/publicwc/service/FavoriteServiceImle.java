package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.Favorite;
import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.repository.FavoriteRepository;
import bitc.fullstack405.publicwc.repository.UsersRepository;
import bitc.fullstack405.publicwc.repository.WcInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImle implements FavoriteService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private WcInfoRepository wcInfoRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public void addFavorite(String userId, int wcId) {

        // userId로 사용자를 찾고, 없으면 예외 발생
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // wcId로 화장실을 찾고, 없으면 예외 발생
        WcInfo wcInfo = wcInfoRepository.findById(wcId)
                .orElseThrow(() -> new RuntimeException("WcInfo not found"));

        var newFavorite = new Favorite();
        newFavorite.setFavoriteUsers(user);
        newFavorite.setFavoriteWc(wcInfo);

        favoriteRepository.save(newFavorite);
    }

    @Override
    public void removeFavorite(String userId, int wcId) {

    }

    @Override
    public void updateFavorite(String userId, int wcId) {

//        Favorite favorite = favoriteRepository.findByUser(user);
//
//        // 중복 추가 방지
//        if (favorite.getFavoriteWc() == wcInfo) {
//            throw new RuntimeException("Already favorite");
//        }

    }


}
