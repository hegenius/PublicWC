package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.Favorite;
import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.entity.WcInfo;

import java.util.List;
import java.util.Optional;

public interface FavoriteService {

    public Favorite addFavorite(Users user, WcInfo wcInfo);

    public List<Favorite> removeFavorite(String userId, int wcId);

    public void updateFavorite(String userId, int wcId);

    public List<Favorite> selectFavoriteList(Users user);

    public Optional<Users> getUserById(String userId);

    public Optional<WcInfo> getWcInfoById(int wcId);

    public Favorite selectFavoriteList(Users user, WcInfo wcInfo);
}
