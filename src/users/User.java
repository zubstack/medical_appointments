package users;

import java.util.ArrayList;

public class User {
    public static int id = 0;
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
        id++;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    static public void registerUser(User user) {
        users.add(user);
    }

    static public void showUsers(){
        for (User user : users) {
            System.out.println(user.getClass().getSimpleName()+ ": " + user.getName());
        }
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

}
