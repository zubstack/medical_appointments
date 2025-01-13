package model;

import java.util.UUID;


public abstract class User {
    final private String ID = UUID.randomUUID().toString();
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public User(String name, String email, String address, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
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
