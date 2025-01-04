package model;

import java.util.ArrayList;

import static ui.UIMenu.message;


public class Auth {
    final private String username;
    final private String userId;
    final private String password;

    static ArrayList<Auth> auths = new ArrayList<>();
    static private User currentUser;

    public Auth(String username, String userId, String password) {
        this.password = password;
        this.username = username;
        this.userId = userId;
    }

    static public void registerNewAuth(Auth auth) {
        auths.add(auth);
    }

    static public void showAuths() {
        for (Auth auth : auths) {
            message.listItem("[Auth]: " + auth.username);
        }
    }

    static private Auth findAuthByUsername(String username) {
        for (Auth auth : auths) {
            if (auth.getUsername().equals(username)) {
                return auth;
            }
        }
        return null;
    }

    public String getPassword() {
        return password;
    }

    private String getUsername() {
        return this.username;
    }

    public String getUserId() {
        return userId;
    }

    static public boolean login(String username, String password) {
        try {
            Auth auth = Auth.findAuthByUsername(username);
            User user = User.findUserById(auth.getUserId());
            if (auth.getPassword().equals(password)) {
                currentUser = user;
                return true;
            } else {
                throw new Exception("");
            }

        } catch (Exception e) {
            message.error("Invalid username or password");
        }

        return false;
    }

    static public void logout(){
        currentUser =  null;
    }

    static public User getCurrentUser() {
        return currentUser;
    }


}
