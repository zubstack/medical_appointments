package ui;

import model.Auth;
import model.Doctor;
import model.Patient;
import model.User;
import repository.AuthRepository;
import repository.DoctorRepository;
import repository.UserRepository;

import java.util.*;

import static ui.UIMenu.message;

public class UIPatientMenu {
    private UserRepository userRepository;
    private DoctorRepository doctorRepository;
    private AuthRepository authRepository;

    private final Scanner scan;
    private final ArrayList<Doctor> availableDoctors = new ArrayList<>();

    public UIPatientMenu(Scanner scan, UserRepository userRepository, DoctorRepository doctorRepository, AuthRepository authRepository) {
        this.scan = scan;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.authRepository = authRepository;
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

        message.field("Birthday: ");
        birthday = scan.nextLine();
        message.field("Blood: ");
        blood = scan.nextLine();
        message.field("Weight: ");
        weight = scan.nextDouble();
        message.field("Height: ");
        height = scan.nextDouble();
        scan.nextLine();

        message.prompt("Register your credentials");
        message.field("Username: ");
        username = scan.nextLine().trim();
        message.field("Password: ");
        password = scan.nextLine().trim();

        Patient patient = new Patient(name, email, address, phoneNumber, birthday, weight, height, blood);
        userRepository.save(patient);
        Auth auth = new Auth(username, patient.getId(), password);
        authRepository.save(auth);

        message.info("New " + patient.getClass().getSimpleName() + " has been registered.");
    }

    void showBookAvailableAppointmentMenu(Patient patient) {
        boolean isConfirmed = false;
        int response;
        int selectedIndex;
        int max;
        int k;
        Map<Integer, Doctor.AvailableAppointment> appointmentsTree = new TreeMap<>();

        updateAvailableAppointments();

        do {
            do {
                k = 0;
                message.prompt("Please select one appointment to be booked: ");

                for (Doctor availableDoctor : availableDoctors) {
                    ArrayList<Doctor.AvailableAppointment> availableAppointments = availableDoctor.getAvailableAppointments();
                    for (Doctor.AvailableAppointment availableAppointment : availableAppointments) {
                        appointmentsTree.put(k, availableAppointment);
                        message.numberedOption(k, availableAppointment.toString());
                        k++;
                    }
                }
                // Last option:
                message.numberedOption(k, "Exit");
                max = k;
                message.option();
                selectedIndex = scan.nextInt();
            } while (selectedIndex < 0 || selectedIndex > max);

            if (selectedIndex == max) {
                return;
            }

            Doctor.AvailableAppointment selectedAppointment = appointmentsTree.get(selectedIndex);
            Doctor selectedDoctor = selectedAppointment.getDoctor();
            message.info("Your selected appointment: ");
            message.info(selectedAppointment.toString());
            message.showConfirmationOptions();
            response = scan.nextInt();

            if (response == 1) {
                Patient.BookedAppointment newBookedAppointment = new Patient.BookedAppointment(selectedAppointment, patient);
                patient.bookAppointment(newBookedAppointment);
                selectedDoctor.removeAvailableAAppointment(selectedAppointment);
                message.info("APPOINTMENT BOOKED");
                isConfirmed = true;
            }
        } while (!isConfirmed);
    }

    void showBookedAppointments(Patient patient) {
        message.info("Your booked appointments: ");
        patient.showBookedAppointments();
    }

    void updateAvailableAppointments() {
        List<Doctor> doctors = doctorRepository.findAll();
        for (Doctor doctor : doctors) {
            if (doctor.hasAvailableAppointments() && !availableDoctors.contains(doctor)) {
                availableDoctors.add(doctor);
            }
        }
    }
}

