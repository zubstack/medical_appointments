package ui;

import model.Auth;
import model.Nurse;
import model.User;

import java.util.InputMismatchException;
import java.util.Scanner;

import static ui.UIMenu.showOptions;

public class UINurseMenu {

    private final Scanner scan;
    public UINurseMenu (Scanner scan){
        this.scan = scan;
    }

    public void showMenu(Nurse nurse) {
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

    public void  showRegistrationMenu (String name, String email, String address, String phoneNumber) {
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
}
