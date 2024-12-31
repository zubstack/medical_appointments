package ui;

import model.Auth;
import model.Doctor;
import model.Patient;
import model.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UIPatientMenu {
    private final Scanner scan;
    private final ArrayList<Doctor> availableDoctors = new ArrayList<>();

    public UIPatientMenu(Scanner scan) {
        this.scan = scan;
    }

    public void showMenu(Patient patient) {
        int response = 0;
        String[] options = new String[]{"Book an appointment", "Show my booked appointments", "Cancel appointment", "Log out"};

        System.out.println("**WELCOME, " + patient.getName() + "**");
        do {
            try {

                UIMenu.showOptions(options);
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        showBookAvailableAppointmentMenu(patient);
                        break;
                    case 2:
                        showBookedAppointments(patient);
                        break;
                    case 3:
                        System.out.println("**Cancel appointment**");
                        break;
                    case 4:
                        Auth.logout();
                        System.out.println("\n**LOGGING OUT**");
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

    void showBookAvailableAppointmentMenu(Patient patient) {
        int response = showAvailableAppointmentsMenu();
        bookSelectedAppointment(response, patient);
        System.out.println("**APPOINTMENT BOOKED**");
    }

    void showBookedAppointments(Patient patient) {
        System.out.println("\nYour booked appointments:");
        patient.showBookedAppointments();
    }

    public int showAvailableAppointmentsMenu() {
        int response;
        int max;
        do {
            System.out.println("\n>> Please select one appointment to be booked: ");
            max = showAllAvailableAppointments();
            System.out.print("\nYour option: ");
            response = scan.nextInt();
            //[PENDING]: User input control.
        } while (response < 0 || response > max);
        return response;
    }

    void bookSelectedAppointment(int response, Patient patient) {
        int i = 0;
        for (Doctor availableDoctor : availableDoctors) {
            for (Doctor.AvailableAppointment availableAppointment : availableDoctor.getAvailableAppointments()) {
                if (i == response) {
                    Patient.BookedAppointment newBookedAppointment = new Patient.BookedAppointment(availableAppointment, availableDoctor, patient);
                    patient.bookAppointment(newBookedAppointment);
                    availableDoctor.removeAvailableAAppointment(availableAppointment);
                    if(!availableDoctor.hasAvailableAppointments()){
                        availableDoctors.remove(availableDoctor);
                    }
                    return;
                }
                i++;
            }
        }
    }

    int showAllAvailableAppointments() {
        int i = 0;
        ArrayList<Doctor> doctors = User.getDoctors();
        for (Doctor doctor : doctors) {
            if (doctor.hasAvailableAppointments() && !availableDoctors.contains(doctor)) {
                availableDoctors.add(doctor);
            }
        }
        for (Doctor availableDoctor : availableDoctors) {
            i = availableDoctor.showAvailableAppointmentsInRow(i);
        }
        return i-1;
    }

}
