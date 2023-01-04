package liked;

public interface LikedDAO<T>{
    void saveLike(T val);
    void deleteLike(T val);

}
