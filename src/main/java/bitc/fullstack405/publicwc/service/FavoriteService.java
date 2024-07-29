package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.Favorite;

public interface FavoriteService {

    public void addFavorite(String userId, int wcId);
    public void removeFavorite(String userId, int wcId);
    public void updateFavorite(String userId, int wcId);
    public List<Favorite> selectFavoriteList() throws Exception;

