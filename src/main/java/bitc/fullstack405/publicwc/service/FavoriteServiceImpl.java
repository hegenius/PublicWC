package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.repository.WcInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
  @Autowired
  private WcInfoRepository wcInfoRepository;

  @Override
  public List<WcInfo> selectFavoriteList() throws Exception {
    return wcInfoRepository.favoriteWcLists();
  }
}