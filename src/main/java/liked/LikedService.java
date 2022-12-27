package liked;

import user.User;

import java.util.List;
public class LikedService {

    CollectionLikedDAO collectionLikedDAO = new CollectionLikedDAO();

    public void saveLike(Like like) {
        collectionLikedDAO.saveLike(like);

    }

    public void deleteLike(Like like) {
       collectionLikedDAO.deleteLike(like);
    }

    public List<Like> getAllLikes() {
        return collectionLikedDAO.getAllLikes();
    }

    public int findUserForId(User usrFromId){
        return collectionLikedDAO.findUserForId(usrFromId);
    }
}
