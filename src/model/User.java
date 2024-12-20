package model;

import java.util.ArrayList;
import java.util.UUID;

public abstract class User {
    final private String ID = UUID.randomUUID().toString();
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    static ArrayList<User> users = new ArrayList<>();

    public User(String name, String email, String address, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    static public void registerNewUser(Doctor user, String username, String password) {
        users.add(user);
        Auth auth = new Auth(username, user.getId(), password);
        Auth.registerNewAuth(auth);
    }

    static public void registerNewUser(Patient user, String username, String password) {
        users.add(user);
        Auth auth = new Auth(username, user.getId(), password);
        Auth.registerNewAuth(auth);
    }

    static public void registerNewUser(Nurse user, String username, String password) {
        users.add(user);
        Auth auth = new Auth(username, user.getId(), password);
        Auth.registerNewAuth(auth);
    }

    static public void showUsers() {
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    static public User findUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public String getId() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName().toUpperCase() + ": Name: " + getName()+ " Email: " + getEmail()+ " Address: " + getAddress()+ " Phone: " + getPhoneNumber();
    }
}
