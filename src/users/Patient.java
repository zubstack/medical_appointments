package users;

import java.util.ArrayList;

public class Patient extends User {
    public static int id = 0;
    private String birthday;
    private double weight;
    private double height;
    private String blood;
    static ArrayList <Patient> patients = new ArrayList<>();

    public Patient(String name, String email, String address, String phoneNumber, String birthday, double weight, double height, String blood) {
        super(name, email, address, phoneNumber);
        this.birthday = birthday;
        this.weight =weight;
        this.height = height;
        this.blood = blood;
        id++;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    static public void registerPatient(Patient patient) {
        patients.add(patient);
    }

    static public void showPatients(){
        for (Patient patient : patients) {
            System.out.println("Patient: " + patient.getName());
        }
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWeight() {
        return weight + " kg.";
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height + " m.";
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }


}
