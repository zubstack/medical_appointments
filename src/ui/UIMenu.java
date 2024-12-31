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
    static UIMessage message;

    private final UIDoctorMenu uiDoctorMenu;
    private final UIPatientMenu uiPatientMenu;
    private final UIDevMenu uiDevMenu;
    private final UINurseMenu uiNurseMenu;

    public UIMenu(Scanner scan) {
        this.scan = scan;
        message = new UIMessage();
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
                showLoggedUserMenu(currentUser);
            } else {
                CLOSE_APP = showInitialMenu();
            }
        }
    }

    public void showLoggedUserMenu(User user) {
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

    public boolean showInitialMenu() {
        int response = 0;
        String[] options = new String[]{"Login", "Sign up", "Exit"};

        message.info("MEDICAL APPOINTMENTS");
        do {
            try {
                showOptions(options);
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        if (showLoginMenu()) {
                            response = options.length;
                        }
                        break;
                    case 2:
                        showRegistrationMenu();
                        break;
                    case 3:
                        message.info("THANK YOU FOR YOUR VISIT.");
                        return true;
                    case 4:
                        uiDevMenu.showDevMenu();
                        break;
                    default:
                        message.error("Invalid option.");
                }
            } catch (Exception e) {
                message.error("Please insert values according the field.");
                scan.next();  // Consume the invalid input
            }

        } while (response != options.length);
        return false;
    }

    private void showRegistrationMenu() {
        message.prompt("Registration. Please help us with some information: ");
        int response = 0;
        String[] options = new String[]{"Doctor", "Patient", "Nurse", "Exit"};
        do {
            try {
                showOptions("What are you?", options);
                response = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                message.error("Please insert values according the field.");
                scan.next();  // Consume the invalid input
            }
        } while (response < 1 || response > options.length);

        if (response != options.length) {
            showRegistrationFromOption(response);
        }
    }

    private void showRegistrationFromOption(int response) {
        String name;
        String email;
        String address;
        String phoneNumber;

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
        message.prompt("Enter your credentials");
        String username;
        String password;

        System.out.print("Username: ");
        username = scan.nextLine().trim();
        System.out.print("Password: ");
        password = scan.nextLine().trim();

        return Auth.login(username, password);
    }

    public static void showOptions(String[] options) {
        message.prompt("Please, select an option:");
        for (int i = 0; i < options.length; i++) {
            System.out.printf("\n(%d). %s", i + 1, options[i]);
        }
        message.option();
    }

    public static void showOptions(String instruction, String[] options) {
        message.prompt(instruction + ": ");
        for (int i = 0; i < options.length; i++) {
            System.out.printf("\n(%d). %s", i + 1, options[i]);
        }
        message.option();
    }

    public static int getMonth(Scanner scan) {
        int response;
        final int min = 0;
        final int max = 3;
        do {
            message.prompt("Select a month from " + MONTHS[min] + " to" + MONTHS[max]);
            for (int i = min; i < max; i++) {
                System.out.printf("(%d).%s\n", i + 1, MONTHS[i]);
            }
            message.option();
            response = scan.nextInt();
        } while (response < min + 1 || response > max);
        return (response - 1);
    }

    public static int getDay(Scanner scan) {
        int response;
        final int min = 1;
        final int max = 27;
        do {
            message.prompt("Insert a day from " + min + " to" + max + ": ");
            response = scan.nextInt();
        } while (response < min || response > max);
        return (response);
    }

    public static int getYear(Scanner scan) {
        int response;
        final int min = 2024;
        final int max = 2025;
        do {
            message.prompt("Insert a year from " + min + " to" + max + ": ");
            response = scan.nextInt();
        } while (response < min || response > max);
        return (response);
    }

}
