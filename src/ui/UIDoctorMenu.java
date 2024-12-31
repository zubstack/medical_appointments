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

    public UIDoctorMenu(Scanner scan) {
        this.scan = scan;
    }

    public void showMenu(Doctor doctor) {
        int response = 0;
        String[] options = new String[]{"Create an appointment", "Show my available appointments", "Show my pending appointments", "Cancel an appointment", "Log out"};

        System.out.println("\n**WELCOME, " + doctor.getName() + "**");
        do {
            try {
                showOptions(options);
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        showCreateAppointmentMenu(doctor);
                        break;
                    case 2:
                        showAvailableAppointments(doctor);
                        break;
                    case 3:
                        System.out.println("**Show pending appointments**");
                        break;
                    case 4:
                        System.out.println("**Cancel appointment**");
                        break;
                    case 5:
                        Auth.logout();
                        System.out.println("**LOGGING OUT**");
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

    public void showRegistrationMenu(String name, String email, String address, String phoneNumber) {
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
    }

    public void showCreateAppointmentMenu(Doctor doctor) {
        Date date = getDate();
        String time = getTime();
        // Here we should ask for confirmation about the data.

        Doctor.AvailableAppointment appointment = new model.Doctor.AvailableAppointment(date, time, doctor);
        doctor.addNewAppointment(appointment);
        System.out.println("**New appointment created **");
    }

    public void showAvailableAppointments(Doctor doctor) {
        doctor.showAvailableAppointments();
    }

    public Date getDate() {
        // Prompt user for date
        return new Date();

    }

    public String getTime() {
        // Prompt user for time
        return "4pm";
    }
}
