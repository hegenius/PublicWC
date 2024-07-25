package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepository;

<<<<<<< Updated upstream
//    @Override
//    public Optional<Users> findByUsername(String username) {
//        return Optional.ofNullable(userRepository.findByUsername(username));
//    }

//    @Override
//    public Optional<Users> findById(Long userId) {
//        return userRepository.findById(userId);
//    }
=======
    // BCryptPasswordEncoder 객체 생성
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
>>>>>>> Stashed changes

    @Override
    public void saveUser(Users user) {
        userRepository.save(user);
    }

    @Override
    public void writeWc(WcInfo wcInfo) {

    }

//    @Override
//    public boolean checkPassword(Users user, String rawPassword) {
//        return passwordEncoder.matches(rawPassword, user.getPassword());
//    }

    public void deleteUser(String userId) {
        userRepository.deleteUsers(userId);
    }


}
