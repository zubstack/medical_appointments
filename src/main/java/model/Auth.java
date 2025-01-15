package model;

import jakarta.persistence.*;
import repository.AuthRepository;
import repository.UserRepository;

import java.util.UUID;

import static ui.UIMenu.message;

@Entity
@Table(name = "auth")
public class Auth {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    final private String ID = UUID.randomUUID().toString();

    @Column(name = "username", nullable = false)
    final private String username;

    @Column(name = "user_id", nullable = false)
    final private String userId;

    @Column(name = "password", nullable = false)
    final private String password;

    @Transient
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
