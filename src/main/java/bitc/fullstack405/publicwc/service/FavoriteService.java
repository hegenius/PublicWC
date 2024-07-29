package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.WcInfo;
import java.util.List;


public interface FavoriteService  {

  List<WcInfo> selectFavoriteList() throws Exception;

}
