package bitc.fullstack405.publicwc.service;

public interface FavoriteService {

    public void addFavorite(String userId, int wcId);
    public void removeFavorite(String userId, int wcId);
    public void updateFavorite(String userId, int wcId);
}
