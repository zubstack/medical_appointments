package users;

import java.util.ArrayList;

public class Auth {
    private String username;
    public int userId;
    private String password;

    static ArrayList<Auth> auths = new ArrayList<>();

    public Auth(String username, int userId, String password){
        this.password = password;
        this.username = username;
        this.userId = userId;
   }

    static public void addNewAuth(Auth auth) {
        auths.add(auth);
    }

    static public void showAuths() {

        for (Auth auth : auths) {
            System.out.println("Auth: " + auth.username);
        }
    }
}
