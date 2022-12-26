package user;

public class User {
    private int id;
    private String name;
    private String img;
    private String cookieId;

    public User() {
    }

    public User(int id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public User(int id, String name, String img, String cookieId) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.cookieId = cookieId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getCookieId() {
        return cookieId;
    }

    @Override
    public String toString() {
        return String.format("User{id='%s', name='%s', img='%s',}", id, name, img);
    }
}
