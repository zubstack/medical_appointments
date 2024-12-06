package ui;

import users.Auth;
import users.Doctor;
import users.Patient;

import java.util.Scanner;

public class UIMenu {
    static final String[] MONTHS = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };
    static Scanner scan = new Scanner(System.in);

    public static void showMenu() {
        int response;
        final int optionsLength = 3;
        System.out.println("Welcome to Medical Appointments");
        do {
            System.out.println(">> Please, select an option:\n(1).Login\n(2).Sign up\n(3).Exit");
            System.out.print("Your option: ");
            response = scan.nextInt();

            switch (response) {
                case 1:
                    System.out.println("Login");
                    break;
                case 2:
                    registerUser();
                    break;
                case optionsLength:
                    System.out.println("Thank you for you visit!.");
                    System.out.println();
                    break;
                case 4:
                    showDevMenu();
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (response != optionsLength);
    }

    static void showPatientMenu() {
        int response;
        final int optionsLength = 3;
        System.out.println("Welcome unknown patient!");
        do {
            System.out.println(">> Select an option:\n(1).Book an appointment\n(2).Cancel appointment\n(3).Exit");
            System.out.print("Your option: ");
            response = scan.nextInt();

            switch (response) {
                case 1:
                    String month = getMonth();
                    System.out.println("Your month: " + month);
                    break;
                case 2:
                    System.out.println("** CANCELING **");
                    break;
                case optionsLength:
                    System.out.println("Good bye unknown patient!.");
                    System.out.println();                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (response != optionsLength);
    }

    static void showDevMenu() {
        int response;
        final int optionsLength = 4;
        System.out.println("DEV MENU");
        do {
            System.out.println(">> Select an option:\n(1).Show doctors\n(2).Show users\n(3).Show auths\n(4).Exit");
            System.out.print("Your option: ");
            response = scan.nextInt();

            switch (response) {
                case 1:
                    Doctor.showDoctors();
                    break;
                case 2:
                    Patient.showPatients();
                    break;
                case 3:
                    Auth.showAuths();
                    break;
                case optionsLength:
                    System.out.println();
                    break;
                default:
                    System.out.println("INVALID OPTION");
            }
        } while (response != optionsLength);
    }

    static String getMonth() {
        int response;
        final int optionsLength = 3;
        do {
            System.out.println(">> Select a month:");
            for (int i = 0; i < optionsLength; i++) {
                System.out.printf("(%d).%s\n", i + 1, MONTHS[i]);
            }
            System.out.print("Your option: ");
            response = scan.nextInt() - 1;
        } while (response < 1 || response > optionsLength);
        return MONTHS[response];
    }

    static void registerUser() {
        String name;
        String email;
        String address;
        String phoneNumber;

        System.out.println(">> Registration. Please help us with some information");
        int response;
        do {
            System.out.println("What are you?:\n(1).Doctor\n(2).Patient");
            System.out.println("Your option: ");
            response = scan.nextInt();
            scan.nextLine();
        } while (response < 1 || response > 2);

        System.out.print("Name: ");
        name = scan.nextLine();
        System.out.print("Email: ");
        email = scan.nextLine();
        System.out.print("Address: ");
        address = scan.nextLine();
        System.out.print("Phone Number: ");
        phoneNumber = scan.nextLine();

        switch (response) {
            case 1:
                registerNewDoctor(name, email, address, phoneNumber);
                break;
            case 2:
                registerNewPatient(name, email, address, phoneNumber);
                break;
        }

    }

    static void registerNewPatient(String name, String email, String address, String phoneNumber) {
        String birthday;
        String blood;
        double weight;
        double height;

        System.out.print("Birthday: ");
        birthday = scan.nextLine();
        System.out.print("Blood: ");
        blood = scan.nextLine();
        System.out.print("Weight: ");
        weight = scan.nextDouble();
        System.out.print("Height: ");
        height = scan.nextDouble();
        scan.nextLine();

        Patient patient = new Patient(name, email, address, phoneNumber, birthday, weight, height, blood);
        Patient.registerPatient(patient);
        registerNewAuth(patient.id);

        System.out.println("OPERATION SUCCEED: New patient registered.");
        System.out.println();
    }

    static void registerNewDoctor(String name, String email, String address, String phoneNumber) {
        String speciality;

        System.out.print("Speciality: ");
        speciality = scan.nextLine();

        Doctor doctor = new Doctor(name, email, address, phoneNumber, speciality);
        Doctor.registerDoctor(doctor);
        registerNewAuth(doctor.id);

        System.out.println("OPERATION SUCCEED: New doctor registered.");
        System.out.println();
    }

    static void registerNewAuth(int userId) {
        System.out.println(">> Register your credentials");
        String username;
        String password;

        System.out.print("Username: ");
        username = scan.nextLine();
        System.out.print("Password: ");
        password = scan.nextLine();

        Auth auth = new Auth(username, userId, password);
        Auth.addNewAuth(auth);
    }

}
