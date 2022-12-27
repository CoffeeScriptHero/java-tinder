package liked;

public class Like {


    private int id;
    private int userFromId;
    private int userForId;


    public Like(int id, int userFromId, int userForId) {
        this.id = id;
        this.userFromId = userFromId;
        this.userForId = userForId;

    }

    public Like(int userFromId, int userForId) {
        this.userFromId = userFromId;
        this.userForId = userForId;

    }


    public Like() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserForId() {
        return userForId;
    }

    public void setUserForId(int userForId) {
        this.userForId = userForId;
    }

    public int getUserFromId() {
        return userFromId;
    }

    public void setUserFromId(int userFromId) {
        this.userFromId = userFromId;
    }
}
