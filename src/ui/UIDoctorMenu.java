package ui;

import model.Auth;
import model.Doctor;
import model.User;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import static ui.UIMenu.showOptions;

public class UIDoctorMenu {

    private final Scanner scan;

    public UIDoctorMenu(Scanner scan){
        this.scan = scan;
    }
    public void showMenu(Doctor doctor) {
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

        Doctor doctor = new Doctor(name, email, address, phoneNumber, speciality);
        User.registerNewUser(doctor, username, password);

        System.out.println("\n**OPERATION SUCCEED: New " + doctor.getClass().getSimpleName() + " has been registered.**");
        System.out.println();
    }
}
