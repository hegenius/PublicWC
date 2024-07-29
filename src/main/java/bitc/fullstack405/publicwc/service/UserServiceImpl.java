package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.repository.UsersRepository;
import bitc.fullstack405.publicwc.repository.WcInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private WcInfoRepository wcInfoRepository;

    @Override
    public void writeWc(WcInfo wcInfo) {
        // 구현이 필요한 경우 구현하세요.
    }

    @Override
    public Optional<Users> findById(String userId) {
        return usersRepository.findById(userId);
    }

    @Override
    public void saveUser(Users user) {
        usersRepository.save(user); // 엔티티를 저장하거나 업데이트합니다.
    }

    @Override
    public boolean checkPassword(Users user, String rawPassword) {
        return usersRepository.correctLogin(user.getId(), rawPassword) > 0;
    }

    @Override
    public void deleteUser(String userId) {
        usersRepository.deleteUsers(userId);
    }

    // 특정 사용자의 즐겨찾기 리스트에 화장실을 추가하는 메서드
    public void addFavorite(String userId, int wcId) {
        // userId로 사용자를 찾고, 없으면 예외 발생
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // wcId로 화장실을 찾고, 없으면 예외 발생
        WcInfo wcInfo = wcInfoRepository.findById(wcId)
                .orElseThrow(() -> new RuntimeException("WcInfo not found"));

        // 사용자 즐겨찾기 리스트에 화장실 추가
        user.getFavoriteWcList().add(wcInfo);

        // 변경 사항 저장
        usersRepository.save(user);
    }
}
