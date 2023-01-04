package liked;

import user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionLikedDAO implements LikedDAO<Like> {

    private final Connection conn;

    public CollectionLikedDAO (Connection conn){
        this.conn = conn;
    }


    @Override
    public void saveLike(Like like) {
        try(PreparedStatement ps = conn.prepareStatement(
                "insert into liked(like_from_id, like_for_id) values (?, ?)")){
            ps.setInt(1, like.getUserFromId());
            ps.setInt(2, like.getUserForId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void deleteLike(Like like) {
        try(PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM liked WHERE like_from_id=? and like_for_id=?")){
            ps.setInt(1, like.getUserFromId());
            ps.setInt(2, like.getUserForId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet getLikesFromUser (User usr){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from liked WHERE like_from_id=?");
            ps.setInt(1, usr.getId());
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

