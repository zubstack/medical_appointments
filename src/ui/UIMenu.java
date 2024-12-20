package ui;

import model.*;

import java.util.Date;
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
                showDoctorMenu((Doctor) user);
                break;
            case "Patient":
                showPatientMenu((Patient) user);
                break;
            case "Nurse":
                showNurseMenu((Nurse) user);
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
                        showDevMenu();
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

    private void showPatientMenu(Patient patient) {
        int response = 0;
        String[] options = new String[]{"Book an appointment", "Cancel appointment", "Log out"};

        System.out.println("**WELCOME, " + patient.getName() + "**");
        do {
            try {

                showOptions(options);
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        String month = getMonth();
                        System.out.println("Your month: " + month);
                        break;
                    case 2:
                        System.out.println("**CANCELING**");
                        break;
                    case 3:
                        Auth.logout();
                        System.out.println("\n**LOGGING OUT**");
                        System.out.println();
                        break;
                    default:
                        System.out.println("[ERROR]: Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR]: Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }
        } while (response != options.length);

    }

    private void showDoctorMenu(Doctor doctor) {
        int response = 0;
        String[] options = new String[]{"Create an appointment", "Show appointment", "Log out"};

        System.out.println("\n**WELCOME, " + doctor.getName() + "**");
        do {
            try {
                showOptions(options);
                response = scan.nextInt();

                switch (response) {
                    case 1:
//                        String month = getMonth(); // getTime()
//                        System.out.println("Your month: " + month);
                        Doctor.AvailableAppointment appointment = new model.Doctor.AvailableAppointment(new Date(), "4pm");
                        doctor.addNewAppointment(appointment);
                        System.out.println("**NEW APPOINTMENT SAVED**");
                        break;
                    case 2:
                        doctor.showAvailableAppointments();
                        break;
                    case 3:
                        Auth.logout();
                        System.out.println("\n**LOGGING OUT**");
                        System.out.println();
                        break;
                    default:
                        System.out.println("[ERROR]: Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR]: Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }
        } while (response != options.length);
    }

    private void showNurseMenu(Nurse nurse) {
        int response = 0;
        String[] options = new String[]{"Create an appointment", "Show appointment", "Log out"};

        System.out.println("\n**WELCOME, " + nurse.getName() + "**");
        do {
            try {
                showOptions(options);
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        System.out.println("**NEW APPOINTMENT SAVED**");
                        break;
                    case 2:
                        System.out.println("**SHOW AVAILABLE APPOINTMENTS**");
                        break;
                    case 3:
                        Auth.logout();
                        System.out.println("\n**LOGGING OUT**");
                        System.out.println();
                        break;
                    default:
                        System.out.println("[ERROR]: Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR]: Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }
        } while (response != options.length);
    }

    private void showDevMenu() {
        int response = 0;
        String[] options = new String[]{"Show users", "Show auths", "Exit"};
        System.out.println("\n**DEV MENU**");
        do {
            try {
                showOptions(options);
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        User.showUsers();
                        break;
                    case 2:
                        Auth.showAuths();
                        break;
                    case 3:
                        System.out.println();
                        break;
                    default:
                        System.out.println("[ERROR]: INVALID OPTION");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR]: Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }

        } while (response != options.length);
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
                showRegisterNewDoctorMenu(name, email, address, phoneNumber);
                break;
            case 2:
                showRegisterNewPatientMenu(name, email, address, phoneNumber);
                break;
            case 3:
                showRegisterNewNurseMenu(name, email, address, phoneNumber);
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

    private void showRegisterNewPatientMenu (String name, String email, String address, String phoneNumber) {
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

        System.out.println("\n**OPERATION SUCCEED: New " + patient.getClass().getSimpleName() + " has been registered.**");
        System.out.println();
    }

    private void  showRegisterNewDoctorMenu (String name, String email, String address, String phoneNumber) {
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

        System.out.println("\n**OPERATION SUCCEED: New " + doctor.getClass().getSimpleName() + " has been registered.**");
        System.out.println();
    }

    private void  showRegisterNewNurseMenu (String name, String email, String address, String phoneNumber) {
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

        Nurse nurse = new Nurse(name, email, address, phoneNumber, speciality);
        User.registerNewUser(nurse, username, password);

        System.out.println("\n**OPERATION SUCCEED: New " + nurse.getClass().getSimpleName() + " has been registered.**");
        System.out.println();
    }

    public void showOptions(String[] options) {
        System.out.println("\n>> Please, select an option:");
        for (int i = 0; i < options.length; i++) {
            System.out.printf("\n(%d). %s", i + 1, options[i]);
        }
        System.out.print("\nYour option: ");
    }
    public void showOptions(String instruction, String[] options) {
        System.out.printf("\n>> %s:", instruction);
        for (int i = 0; i < options.length; i++) {
            System.out.printf("\n(%d). %s", i + 1, options[i]);
        }
        System.out.print("\nYour option: ");
    }

}

//TODO: Abstract menus, book an appointment functionality, USE CASE diagram
