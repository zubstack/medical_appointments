package ui;

import model.Auth;
import model.Doctor;
import model.Patient;
import model.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static ui.UIMenu.message;

public class UIPatientMenu {
    private final Scanner scan;
    private final ArrayList<Doctor> availableDoctors = new ArrayList<>();

    public UIPatientMenu(Scanner scan) {
        this.scan = scan;
    }

    public void showMenu(Patient patient) {
        int response = 0;
        String[] options = new String[]{"Book an appointment", "Show my booked appointments", "Cancel appointment", "Log out"};
        message.info("WELCOME, " + patient.getName());
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
                        message.info("Cancel appointment");
                        break;
                    case 4:
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

        message.prompt("Register your credentials");
        System.out.print("Username: ");
        username = scan.nextLine().trim();
        System.out.print("Password: ");
        password = scan.nextLine().trim();

        Patient patient = new Patient(name, email, address, phoneNumber, birthday, weight, height, blood);
        User.registerNewUser(patient, username, password);

        message.info("New " + patient.getClass().getSimpleName() + " has been registered.");
    }

    void showBookAvailableAppointmentMenu(Patient patient) {
        Object selectedIndex = selectAnAppointment();
        if(selectedIndex != null){
            bookSelectedAppointment((int) selectedIndex, patient);
            message.info("APPOINTMENT BOOKED");
        }

    }

    void showBookedAppointments(Patient patient) {
        message.info("Your booked appointments: ");
        patient.showBookedAppointments();
    }

    public Object selectAnAppointment() {
        int selectedIndex;
        int max;
        do {
            message.prompt("Please select one appointment to be booked: ");
            max = showAllAvailableAppointmentsMenu();
            System.out.print("\nYour option: ");
            selectedIndex = scan.nextInt();
        } while (selectedIndex < 0 || selectedIndex > max);
        if(selectedIndex == max){
            return null;
        }else{
            return selectedIndex;
        }
    }

    void bookSelectedAppointment(int selectedIndex, Patient patient) {
        int i = 0;
        for (Doctor availableDoctor : availableDoctors) {
            for (Doctor.AvailableAppointment availableAppointment : availableDoctor.getAvailableAppointments()) {
                if (i == selectedIndex) {
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

    int showAllAvailableAppointmentsMenu() {
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
        // Exit option:
        System.out.printf("\n(%d) %s\n", i, "Exit");
        return i;
    }

}
