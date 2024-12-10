package ui;

import users.Auth;
import users.Doctor;
import users.Patient;
import users.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UIMenu {
    static final String[] MONTHS = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };
    final private Scanner scan;

    public UIMenu(Scanner scan) {
        this.scan = scan;
    }


    public void showMenu() {
        boolean CLOSE_APP = false;
        while (!CLOSE_APP) {
            User currentUser = Auth.getCurrentUser();
            if (currentUser != null) {
                showUserMenu(currentUser);
            } else {
                CLOSE_APP = showWelcomeMenu();
            }
        }
    }

    public void showUserMenu(User user) {
        String userClass = user.getClass().getSimpleName();
        switch (userClass) {
            case "Doctor":
                showDoctorMenu(user);
                break;
            case "Patient":
                showPatientMenu(user);
                break;
        }
    }

    public boolean showWelcomeMenu() {
        int response = 0;
        final int optionsLength = 3;
        System.out.println("**WELCOME TO MEDICAL APPOINTMENTS**");
        do {
            try {
                System.out.println("\n>> Please, select an option:\n(1).Login\n(2).Sign up\n(3).Exit");
                System.out.print("Your option: ");
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        if (login()) {
                            System.out.println("**USER LOGGED IN**");
                            response = optionsLength;
                        }
                        break;
                    case 2:
                        registerUser();
                        break;
                    case optionsLength:
                        System.out.println("**THANK YOU FOR YOUR VISIT**");
                        System.out.println();
                        return true;
                    case 4:
                        showDevMenu();
                        break;
                    default:
                        System.out.println("[ERROR]: Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR]: Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }

        } while (response != optionsLength);
        return false;
    }

    private void showPatientMenu(User user) {
        int response = 0;
        final int optionsLength = 3;
        System.out.println("**WELCOME, " + user.getName()+"**");
        do {
            try {
                System.out.println("\n>> Select an option:\n(1).Book an appointment\n(2).Cancel appointment\n(3).Log out");
                System.out.print("Your option: ");
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        String month = getMonth();
                        System.out.println("Your month: " + month);
                        break;
                    case 2:
                        System.out.println("**CANCELING**");
                        break;
                    case optionsLength:
                        Auth.logout();
                        System.out.println("**LOGGING OUT**");
                        System.out.println();
                        break;
                    default:
                        System.out.println("[ERROR]: Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR]: Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }
        } while (response != optionsLength);

    }

    private void showDoctorMenu(User user) {
        int response = 0;
        final int optionsLength = 3;
        System.out.println("\n**WELCOME, " + user.getName()+"**");
        do {
            try {
                System.out.println("\n>> Select an option:\n(1).Book an appointment\n(2).Cancel appointment\n(3).Log out");
                System.out.print("Your option: ");
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        String month = getMonth();
                        System.out.println("Your month: " + month);
                        break;
                    case 2:
                        System.out.println("**CANCELING**");
                        break;
                    case optionsLength:
                        Auth.logout();
                        System.out.println("**LOGGING OUT**");
                        System.out.println();
                        break;
                    default:
                        System.out.println("[ERROR]: Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR]: Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }
        } while (response != optionsLength);
    }

    private void showDevMenu() {
        int response = 0;
        final int optionsLength = 3;
        System.out.println("**DEV MENU**");
        do {
            try {
                System.out.println("\n>> Select an option:\n(1).Show users\n(2).Show auths\n(3).Exit");
                System.out.print("Your option: ");
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        User.showUsers();
                        break;
                    case 2:
                        Auth.showAuths();
                        break;
                    case optionsLength:
                        System.out.println();
                        break;
                    default:
                        System.out.println("[ERROR]: INVALID OPTION");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR]: Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }

        } while (response != optionsLength);
    }

    private String getMonth() {
        int response;
        final int optionsLength = 3;
        do {
            System.out.println("\n>> Select a month:");
            for (int i = 0; i < optionsLength; i++) {
                System.out.printf("(%d).%s\n", i + 1, MONTHS[i]);
            }
            System.out.print("Your option: ");
            response = scan.nextInt() - 1;
        } while (response < 1 || response > optionsLength);
        return MONTHS[response];
    }

    private void registerUser() {
        String name;
        String email;
        String address;
        String phoneNumber;

        System.out.println("\n>> Registration. Please help us with some information");
        int response = 0;
        do {
            try {
                System.out.println("What are you?:\n(1).Doctor\n(2).Patient");
                System.out.print("Your option: ");
                response = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("[ERROR]: Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }
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

    private void registerNewPatient(String name, String email, String address, String phoneNumber) {
        String birthday;
        String blood;
        double weight;
        double height;
        String username;
        String password;

        System.out.print("Birthday: ");
        birthday = scan.nextLine();
        System.out.print("Blood: ");
        blood = scan.nextLine();
        System.out.print("Weight: ");
        weight = scan.nextDouble();
        System.out.print("Height: ");
        height = scan.nextDouble();
        scan.nextLine();
        System.out.println("\n>> Register your credentials");
        System.out.print("Username: ");
        username = scan.nextLine().trim();
        System.out.print("Password: ");
        password = scan.nextLine().trim();

        Patient patient = new Patient(name, email, address, phoneNumber, birthday, weight, height, blood);
        User.registerNewUser(patient, username, password);

        System.out.println("**OPERATION SUCCEED: New " + patient.getClass().getSimpleName() + " has been registered.**");
        System.out.println();
    }

    private void registerNewDoctor(String name, String email, String address, String phoneNumber) {
        String speciality;
        String username;
        String password;

        System.out.print("Speciality: ");
        speciality = scan.nextLine();
        System.out.println("\n>> Register your credentials");
        System.out.print("Username: ");
        username = scan.nextLine().trim();
        System.out.print("Password: ");
        password = scan.nextLine().trim();

        Doctor doctor = new Doctor(name, email, address, phoneNumber, speciality);
        User.registerNewUser(doctor, username, password);

        System.out.println("**OPERATION SUCCEED: New " + doctor.getClass().getSimpleName() + " has been registered.**");
        System.out.println();
    }

//    private void registerNewAuth(String userId) {
//        System.out.println("\n>> Register your credentials");
//        String username;
//        String password;
//
//        System.out.print("Username: ");
//        username = scan.nextLine().trim();
//        System.out.print("Password: ");
//        password = scan.nextLine().trim();
//
//
//    }

    private boolean login() {
        scan.nextLine();
        System.out.println("\n>> Enter your credentials");
        String username;
        String password;

        System.out.print("Username: ");
        username = scan.nextLine().trim();
        System.out.print("Password: ");
        password = scan.nextLine().trim();

        return Auth.login(username, password);
    }

}

//TODO: Abstract menus, book an appointment functionality, USE CASE diagram
