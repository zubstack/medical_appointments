package ui;

import model.Auth;
import model.Patient;
import model.User;

import java.util.InputMismatchException;
import java.util.Scanner;

import static ui.UIMenu.MONTHS;

public class UIPatientMenu {
    private final Scanner scan;
    public UIPatientMenu(Scanner scan){
        this.scan = scan;
    }

    public void showMenu(Patient patient) {
        int response = 0;
        String[] options = new String[]{"Book an appointment", "Cancel appointment", "Log out"};

        System.out.println("**WELCOME, " + patient.getName() + "**");
        do {
            try {

                UIMenu.showOptions(options);
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

    public void showRegistrationMenu (String name, String email, String address, String phoneNumber) {
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

    public  String getMonth() {
        int response;
        final int optionsLength = 3;
        do {
            System.out.println("\n>> Select a month:");
            for (int i = 0; i < optionsLength; i++) {
                System.out.printf("(%d).%s\n", i + 1, MONTHS[i]);
            }
            System.out.print("Your option: ");
            response = scan.nextInt();
        } while (response < 1 || response > optionsLength);
        return MONTHS[response -1];
    }

}
