package liked;

import user.User;

import java.util.List;

public interface LikedDAO {
    void saveLike(Like like);
    void deleteLike(Like like);
    List<Like> getAllLikes ();
   int findUserForId(User usr);
}
