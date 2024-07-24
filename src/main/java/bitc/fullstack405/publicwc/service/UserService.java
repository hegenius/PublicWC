package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.repository.UsersRepository;
import bitc.fullstack405.publicwc.repository.WcInfoRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

//    회원가입하는 매서드
    public void saveUser(Users user);

//    게시글 등록 매서드
    public void writeWc(WcInfo wcInfo);


}