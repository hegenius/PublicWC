package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Favorite;
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class WcFavoriteController {

  @Autowired
  private FavoriteService favoriteService;


  @GetMapping()
  public ModelAndView selectFavoriteList() throws Exception {
    ModelAndView mv = new ModelAndView("/index");

    List<Favorite> favoriteList = favoriteService.selectFavoriteList();
    mv.addObject("favoriteList", favoriteList);

    return mv;
  }
}
