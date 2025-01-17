package model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "auth")
public class Auth {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    final private String ID = UUID.randomUUID().toString();

    @Column(name = "username", nullable = false)
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    static private User currentUser;

    public Auth() {}

    public Auth(String username, String password, User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.username = username;
        this.password = password;
        this.user = user;
    }

    public String getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
