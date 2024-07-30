package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Favorite;
import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.service.FavoriteService;
import bitc.fullstack405.publicwc.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
public class WcFavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;

    @GetMapping({"/", ""})
    public ModelAndView selectFavoriteList(HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");

        if (session.getAttribute("userId") != null) {
            String userId = session.getAttribute("userId").toString();
            Optional<Users> user = userService.findById(userId);

            if (user.isPresent()) {
                List<Favorite> favoriteList = favoriteService.selectFavoriteList(user.get());
                mv.addObject("favoriteList", favoriteList);
            }
        }
        return mv;
    }
}
