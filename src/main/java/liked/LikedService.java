package liked;

import user.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikedService {

    private final CollectionLikedDAO collectionLikedDAO;

    public LikedService (Connection conn) {
        this.collectionLikedDAO = new CollectionLikedDAO(conn);
    }

    public void saveLike(Like like) {
        collectionLikedDAO.saveLike(like);

    }

    public void deleteLike(Like like) {
       collectionLikedDAO.deleteLike(like);
    }


    public List<Like> getLikesFromUser (User usr){
        List<Like> liked = new ArrayList<>();
       try {
           ResultSet lkd = this.collectionLikedDAO.getLikesFromUser(usr);

           while (lkd.next()) {
               liked.add(new Like(lkd.getInt("like_from_id"), lkd.getInt("like_for_id")));
           }
       } catch (SQLException e) {
                throw new RuntimeException(e);
       }
        return liked;
    }

}
