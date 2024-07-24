package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.model.User;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long userId);
    void saveUser(User user);
    boolean checkPassword(User user, String rawPassword);
    void deleteUser(Long userId);
}
