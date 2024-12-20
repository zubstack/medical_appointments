package ui;

import model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UIMenu {
    static final String[] MONTHS = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };
    final private Scanner scan;
    private final UIDoctorMenu uiDoctorMenu;
    private final UIPatientMenu uiPatientMenu;
    private final UIDevMenu uiDevMenu;
    private final UINurseMenu uiNurseMenu;

    public UIMenu(Scanner scan) {
        this.scan = scan;
        this.uiDoctorMenu = new UIDoctorMenu(scan);
        this.uiPatientMenu = new UIPatientMenu(scan);
        this.uiDevMenu = new UIDevMenu(scan);
        this.uiNurseMenu = new UINurseMenu(scan);
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
                uiDoctorMenu.showMenu((Doctor) user);
                break;
            case "Patient":
                uiPatientMenu.showMenu((Patient) user);
                break;
            case "Nurse":
                uiNurseMenu.showMenu((Nurse) user);
                break;
        }
    }

    public boolean showWelcomeMenu() {
        int response = 0;
        String[] options = new String[]{"Login", "Sign up", "Exit"};

        System.out.println("**WELCOME TO MEDICAL APPOINTMENTS**");
        do {
            try {
                showOptions(options);
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        if (showLoginMenu()) {
                            System.out.println("\n**USER LOGGED IN**");
                            response = options.length;
                        }
                        break;
                    case 2:
                        showRegistrationMenu();
                        break;
                    case 3:
                        System.out.println("**THANK YOU FOR YOUR VISIT**");
                        System.out.println();
                        return true;
                    case 4:
                        uiDevMenu.showDevMenu();
                        break;
                    default:
                        System.out.println("[ERROR]: Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR]: Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }

        } while (response != options.length);
        return false;
    }
    private void showRegistrationMenu() {
        String name;
        String email;
        String address;
        String phoneNumber;

        System.out.println("\n>> Registration. Please help us with some information");
        int response = 0;
        String[] options = new String[]{"Doctor", "Patient", "Nurse"};
        do {
            try {
                showOptions("What are you?", options);
                response = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("[ERROR]: Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }
        } while (response < 1 || response > options.length);

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
                uiDoctorMenu.showRegistrationMenu(name, email, address, phoneNumber);
                break;
            case 2:
                uiPatientMenu.showRegistrationMenu(name, email, address, phoneNumber);
                break;
            case 3:
                uiNurseMenu.showRegistrationMenu(name, email, address, phoneNumber);
                break;
        }

    }

    private boolean showLoginMenu() {
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

    public static void showOptions(String[] options) {
        System.out.println("\n>> Please, select an option:");
        for (int i = 0; i < options.length; i++) {
            System.out.printf("\n(%d). %s", i + 1, options[i]);
        }
        System.out.print("\nYour option: ");
    }
    public static void showOptions(String instruction, String[] options) {
        System.out.printf("\n>> %s:", instruction);
        for (int i = 0; i < options.length; i++) {
            System.out.printf("\n(%d). %s", i + 1, options[i]);
        }
        System.out.print("\nYour option: ");
    }

}
