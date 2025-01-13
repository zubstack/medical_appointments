package model;

import repository.AuthRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.UUID;

import static ui.UIMenu.message;


public class Auth {
    final private String ID = UUID.randomUUID().toString();
    final private String username;
    final private String userId;
    final private String password;

    static private User currentUser;

    public Auth(String username, String userId, String password) {
        this.password = password;
        this.username = username;
        this.userId = userId;
    }

    public String getId() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getUserId() {
        return userId;
    }

    static public boolean login(String username, String password, UserRepository userRepository, AuthRepository authRepository) {
        try {
            Auth auth = authRepository.findByUsername(username);
            User user = userRepository.findById(auth.getUserId());
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
