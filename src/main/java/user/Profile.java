package user;

import java.util.ArrayList;

public class Profile {

    public Profile(){
    }

    public User user1 = new User(1,
            "Volodymyr",
            "https://www.president.gov.ua/storage/j-image-storage/14/34/45/b6638a3f2f9eb132a2315e2bebabcd3e_1572854231_extra_large.jpeg");
    public User user2 = new User(2,
            "Borys",
            "https://media.slovoidilo.ua/media/publications/17/167471/167471-1_large.jpg");
    public User user3 = new User(3,
            "Dyda",
            "https://commons.wikimedia.org/wiki/File:President_of_Poland_Andrzej_Duda_Full_Resolution_(cropped).jpg?uselang=ru");
    public User user4 = new User(4,
            "Scholts",
            "https://s.mind.ua/img/forall/a/202432/10.jpeg?1655390305");
    public User user5 = new User(5,
            "Emanyel",
            "https://uatv.ua/wp-content/uploads/2021/08/makron.jpeg");
    public User user6 = new User(6,
            "Oleksii",
            "https://cdn.livenetwork.media/images/thumbnail/976x549/63a99e0e11a4b-voyna-29.webp");
    public User user7 = new User(7,
            "Valerii",
            "https://upload.wikimedia.org/wikipedia/commons/2/2f/Lieutenant_General_Valerii_Zaluzhnyi.jpg");
    public User user8 = new User(8,
            "Oleksii",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Oleksii_Reznikov_%28portrait%29.jpg/1024px-Oleksii_Reznikov_%28portrait%29.jpg");

    public ArrayList<User> selectUsers(){
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
}
