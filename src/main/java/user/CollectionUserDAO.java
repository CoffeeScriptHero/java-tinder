package user;

import javax.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class CollectionUserDAO implements UserDAO {
    private static CollectionUserDAO instance = null;
    private final String SELECT_BY_ID = "select * from users where id=?";
    private final String SELECT_BY_EMAIL = "select * from users where email=?";
    private final String SELECT_USER_BY_COOKIE = "select * from users where cookie_id=?";
    private final String SELECT_DIALOGUE = "select * from messages\n" +
            "where user_id_from=?\n" +
            "and user_id_to=?\n" +
            "union\n" +
            "select * from messages\n" +
            "where user_id_from=?\n" +
            "and user_id_to=?\n" +
            "order by date_time";
    private final String INSERT_MESSAGE = "insert into messages (user_id_from, user_id_to, message)" +
            "values (?, ?, ?)";
    private final String INSERT_USER = "insert into users (img, email, password, name, cookie_id)" +
            " values (?, ?, ?, ?, ?)";
    private final Connection conn;
    private final String[] images;
    private User mainUser;
    private User usr;

    private User user1 = new User(1,
            "Volodymyr",
            "https://www.president.gov.ua/storage/j-image-storage/14/34/45/b6638a3f2f9eb132a2315e2bebabcd3e_1572854231_extra_large.jpeg");
    private User user2 = new User(2,
            "Borys",
            "https://media.slovoidilo.ua/media/publications/17/167471/167471-1_large.jpg");
    private User user3 = new User(3,
            "Dyda",
            "https://upload.wikimedia.org/wikipedia/commons/b/bd/President_of_Poland_Andrzej_Duda_Full_Resolution_%28cropped%29.jpg");
    private User user4 = new User(4,
            "Scholts",
            "https://s.mind.ua/img/forall/a/202432/10.jpeg?1655390305");
    private User user5 = new User(5,
            "Emanyel",
            "https://uatv.ua/wp-content/uploads/2021/08/makron.jpeg");
    private User user6 = new User(6,
            "Oleksii",
            "https://cdn.livenetwork.media/images/thumbnail/976x549/63a99e0e11a4b-voyna-29.webp");
    private User user7 = new User(7,
            "Valerii",
            "https://upload.wikimedia.org/wikipedia/commons/2/2f/Lieutenant_General_Valerii_Zaluzhnyi.jpg");
    private User user8 = new User(8,
            "Oleksii",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Oleksii_Reznikov_%28portrait%29.jpg/1024px-Oleksii_Reznikov_%28portrait%29.jpg");


    public CollectionUserDAO(Connection conn) {
        this.conn = conn;
        this.mainUser = new User();
        this.images = new String[]{
                "https://api.time.com/wp-content/uploads/2016/07/vice-president-joe-biden-national-ice-cream-day_01-web1.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Smiley.svg/1200px-Smiley.svg.png",
                "https://www.purina.com.my/sites/default/files/styles/ttt_image_510/public/2021-02/BREED%20Hero%20Mobile_0004_welsh_corgi_pembroke.jpg?itok=OUx5d899",
                "https://upload.wikimedia.org/wikipedia/commons/d/d8/Oxford_blue.png"
        };
    }

    public static CollectionUserDAO getInstance(Connection conn) {
        if (instance == null) {
            instance = new CollectionUserDAO(conn);
        }
        return instance;
    }

    private String getRandomImg() {
        return this.images[new Random().nextInt(this.images.length)];
    }

    public void setMainUser(String cookieId) {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_USER_BY_COOKIE)) {
            stmt.setString(1, cookieId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    this.mainUser = new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("img"),
                            rs.getString("cookie_id")
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private ArrayList<User> selectUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        return users;
    }

    public User getMainUser() {
        return mainUser;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return selectUsers();
    }

    @Override
    public User getUserById(int id) {
        return selectUsers().get(id);
    }

    @Override
    public User getByCookie(Cookie cookie) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * from users WHERE cookie_id=?")) {
            ps.setString(1, cookie.getValue());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usr = new User(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("img"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usr;
    }



    @Override
    public Optional<User> getById(int id) {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.of(
                        new User(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("img")
                        )
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_BY_EMAIL)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.of(
                        new User(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("img"),
                                rs.getString("cookie_id"),
                                rs.getString("password")
                        )
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Message> getDialogue(int senderId, int receiverId) {
        List<Message> messages = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_DIALOGUE)) {
            stmt.setInt(1, senderId);
            stmt.setInt(2, receiverId);
            stmt.setInt(3, receiverId);
            stmt.setInt(4, senderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    messages.add(
                            new Message(
                                    rs.getInt("user_id_to"),
                                    rs.getInt("user_id_from"),
                                    rs.getString("message"),
                                    rs.getTimestamp("date_time").toLocalDateTime()
                            )
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return messages;
    }

    @Override
    public void addMessage(int senderId, int receiverId, String message) {
        String insertSql = "insert into messages (user_id_from, user_id_to, message)" +
                "values (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            stmt.setInt(1, senderId);
            stmt.setInt(2, receiverId);
            stmt.setString(3, message);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void add(String email, String name, String password, String cookieId) {
        String insertSql = "insert into users (img, email, password, name, cookie_id)" +
                " values (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            stmt.setString(1, getRandomImg());
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, name);
            stmt.setString(5, cookieId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
