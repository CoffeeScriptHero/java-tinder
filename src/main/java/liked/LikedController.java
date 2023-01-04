package liked;

import user.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LikedController {
    LikedService likedService;
    public LikedController (Connection conn){
        this.likedService = new LikedService(conn);
    }

    public void saveLike(Like like) {
        likedService.saveLike(like);

    }

    public void deleteLike(Like like) {
        likedService.deleteLike(like);
    }


    public List<Like> getLikesFromUser (User usr) throws SQLException {
        return likedService.getLikesFromUser(usr);
    }

}
