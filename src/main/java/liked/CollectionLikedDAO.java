package liked;

import user.User;
import user.UserController;

import java.util.ArrayList;
import java.util.List;

public class CollectionLikedDAO implements LikedDAO {

    ArrayList<Like> likes = new ArrayList();

    @Override
    public void saveLike(Like like) {
        likes.add(like);

    }

    @Override
    public void deleteLike(Like like) {
        likes.remove(like);
    }

    @Override
    public List<Like> getAllLikes() {
        return likes;
    }

    @Override
    public int findUserForId(User usr) {
            int id = usr.getId();
            //choose from db id users "for" by user's "from" id
            return 0;
    }
}

