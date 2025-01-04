package ui;

import model.Auth;
import model.Doctor;
import model.User;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import static ui.UIMenu.*;

public class UIDoctorMenu {

    private final Scanner scan;

    public UIDoctorMenu(Scanner scan) {
        this.scan = scan;
    }

    public void showMenu(Doctor doctor) {
        int response = 0;
        String[] options = new String[]{"Create an appointment", "Show my available appointments", "Show my pending appointments", "Cancel an appointment", "Log out"};

        message.info("WELCOME, Dr. " + doctor.getName());
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
                        message.info("Show pending appointments");
                        break;
                    case 4:
                        message.info("Cancel appointment");
                        break;
                    case 5:
                        Auth.logout();
                        message.info("LOGGING OUT");
                        break;
                    default:
                        message.error("Invalid option.");
                }
            } catch (InputMismatchException e) {
                message.error("Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }
        } while (response != options.length);
    }

    public void showRegistrationMenu(String name, String email, String address, String phoneNumber) {
        String speciality;
        String username;
        String password;

        message.field("Speciality: ");
        speciality = scan.nextLine();
        message.prompt("Register your credentials: ");
        message.field("Username: ");
        username = scan.nextLine().trim();
        message.field("Password: ");
        password = scan.nextLine().trim();

        Doctor doctor = new Doctor(name, email, address, phoneNumber, speciality);
        User.registerNewUser(doctor, username, password);

        message.info("New " + doctor.getClass().getSimpleName() + " has been registered.");
    }

    public void showCreateAppointmentMenu(Doctor doctor) {
        String date;
        String time;
        boolean isConfirmed = false;
        int response;
        Doctor.AvailableAppointment appointment;

        do {
            date = getDateForm();
            time = getTimeForm();
            appointment = new model.Doctor.AvailableAppointment(date, time, doctor);

            message.info("New appointment: " + appointment);
            message.prompt("Confirm appointment:\n(1). YES\n(2). NO\n(3). EXIT");
            message.option();
            response = scan.nextInt();

            switch (response) {
                case 1:
                    isConfirmed = true;
                    doctor.addNewAppointment(appointment);
                    message.info("New appointment created.");
                    break;
                case 3:
                    isConfirmed = true;
                    break;
            }

        } while (!isConfirmed);
    }

    public void showAvailableAppointments(Doctor doctor) {
        message.info("Available appointments");
        doctor.showAvailableAppointments();
    }

    public String getDateForm() {
        int day, month, year;

        day = getDay(scan, 1, 27);
        month = getMonth(scan, 0, 3);
        year = getYear(scan, 2025, 2026);

        return day + "/" + month + "/" + year + "/";

    }

    public String getTimeForm() {
        int hour, minutes;

        message.prompt("Insert time:");
        message.field("Hours: ");
        hour = scan.nextInt();
        message.field("Minutes: ");
        minutes = scan.nextInt();

        return hour + "h" + minutes;
    }
}
