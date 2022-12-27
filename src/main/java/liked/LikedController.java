package liked;

import user.User;

import java.util.List;

public class LikedController {
    LikedService likedService = new LikedService();

    public void saveLike(Like like) {
        likedService.saveLike(like);

    }

    public void deleteLike(Like like) {
        likedService.deleteLike(like);
    }

    public List<Like> getAllLikes() {
        return likedService.getAllLikes();
    }

    public int findUserForId(User usr){
        return likedService.findUserForId(usr);
    }


}
