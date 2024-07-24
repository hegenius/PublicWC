package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.repository.UsersRepository;
import bitc.fullstack405.publicwc.repository.WcInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private WcInfoRepository wcInfoRepository;

//    회원가입하는 매서드
    public void saveUser(Users user) {

        userRepository.save(user);
    }

//    게시글 등록 매서드
    public void writeWc(WcInfo wcInfo) {

        wcInfoRepository.save(wcInfo);
    }

}