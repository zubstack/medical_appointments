package ui;

import model.Auth;
import model.Doctor;
import model.Patient;
import repository.*;
import service.AuthService;

import java.util.*;

import static ui.UIMenu.message;

public class UIPatientMenu {
    private final DoctorRepository doctorRepository;
    private final AuthRepository authRepository;
    private final AvailableAppointmentRepository availableAppointmentRepository;
    private final BookedAppointmentRepository bookedAppointmentRepository;

    private final Scanner scan;
    private final Set<Doctor> availableDoctors = new HashSet<>();

    public UIPatientMenu(Scanner scan, UserRepository userRepository, DoctorRepository doctorRepository, AuthRepository authRepository, AvailableAppointmentRepository availableAppointmentRepository, BookedAppointmentRepository bookedAppointmentRepository) {
        this.scan = scan;
        this.doctorRepository = doctorRepository;
        this.authRepository = authRepository;
        this.availableAppointmentRepository = availableAppointmentRepository;
        this.bookedAppointmentRepository = bookedAppointmentRepository;
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
                        AuthService.logout();
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

    public void showRegistrationMenu(String name,String paSurname, String maSurname, String email, String address, String phoneNumber) {
        String birthday;
        String blood;
        double weight;
        double height;
        String username;
        String password;

        message.field("Birthday: ");
        birthday = scan.nextLine();

        do {
            message.field("Blood (\"A+\", \"A-\", \"B+\", \"B-\", \"O+\", \"O-\", \"AB+\", \"AB-\"): ");
            blood = scan.nextLine();
        } while (!isValidBloodType(blood));

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

        registerNewPatient(name, paSurname, maSurname,email, address, phoneNumber, birthday, weight, height, blood, username, password);

        message.info("New [PATIENT] has been registered.");
    }

    private void registerNewPatient(String name, String paSurname, String maSurname, String email, String address, String phoneNumber, String birthday, Double weight, Double height, String blood, String username, String password) {
        Patient patient = new Patient(name,paSurname, maSurname, email, address, phoneNumber, birthday, weight, height, blood);
        Auth auth = new Auth(username, password, patient);
        authRepository.save(auth);
    }

    void showBookAvailableAppointmentMenu(Patient patient) {
        int CONFIRM = 1;
        boolean isConfirmed = false;
        int response;
        int selectedIndex;
        int k;
        Map<Integer, Doctor.AvailableAppointment> appointmentsTree = new TreeMap<>();

        updateAvailableAppointments();

        do {
            k = 0;
            message.prompt("Please select one appointment to be booked: ");

            if (!availableDoctors.isEmpty()) {
                for (Doctor availableDoctor : availableDoctors) {
                    List<Doctor.AvailableAppointment> availableAppointments = availableAppointmentRepository.findByDoctorId(availableDoctor.getId());
                    for (Doctor.AvailableAppointment appointment : availableAppointments) {
                        appointmentsTree.put(k, appointment);
                        message.numberedOption(k, appointment.toString());
                        k++;
                    }
                }

            } else {
                message.info("No available appointments for the moment.");
            }

            // Last option:
            message.numberedOption(k, "Exit");

            selectedIndex = getValidAppointmentIndex(k);

            if (selectedIndex == k) {
                return;
            }

            Doctor.AvailableAppointment selectedAppointment = appointmentsTree.get(selectedIndex);
            message.info("Your selected appointment: ");
            message.info(selectedAppointment.toString());
            message.showConfirmationOptions();
            response = scan.nextInt();

            if (response == CONFIRM) {
                Patient.BookedAppointment newBookedAppointment = new Patient.BookedAppointment(selectedAppointment, patient);
                bookAnAppointment(newBookedAppointment, selectedAppointment);
                message.info("APPOINTMENT BOOKED");
                isConfirmed = true;
            }
        } while (!isConfirmed);
    }

    void showBookedAppointments(Patient patient) {
        message.info("Your booked appointments: ");
        showBookedAppointments(patient.getId());
    }

    void updateAvailableAppointments() {
        List<Doctor> doctors = doctorRepository.findAll();
        for (Doctor doctor : doctors) {
            if (hasAvailableAppointments(doctor.getId())) {
                availableDoctors.add(doctor); // Set automatically prevents duplicates
            }
        }
    }


    public boolean hasAvailableAppointments(String doctorId) {
        List<Doctor.AvailableAppointment> availableAppointments = availableAppointmentRepository.findByDoctorId(doctorId);
        if (availableAppointments == null) {
            return false;
        }
        return !availableAppointments.isEmpty();
    }

    public void showBookedAppointments(String patientId) {
        List<Patient.BookedAppointment> patientBookedAppointments = bookedAppointmentRepository.findByPatientId(patientId);
        if (patientBookedAppointments != null) {
            if (!patientBookedAppointments.isEmpty()) {
                patientBookedAppointments.forEach(message::listItem);
            }
        } else {
            message.info("EMPTY");
        }
    }

    private boolean isValidBloodType(String blood) {
        return Arrays.asList("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-").contains(blood);
    }

    private int getValidAppointmentIndex(int max) {
        int selectedIndex;
        do {
            message.option();
            selectedIndex = scan.nextInt();
        } while (selectedIndex < 0 || selectedIndex > max);
        return selectedIndex;
    }

    // Transaction: Book an appointment
    void bookAnAppointment(Patient.BookedAppointment newBookedAppointment, Doctor.AvailableAppointment selectedAppointment) {
        bookedAppointmentRepository.save(newBookedAppointment);
        availableAppointmentRepository.delete(selectedAppointment);
        if (!hasAvailableAppointments(selectedAppointment.getDoctor().getId())) {
            availableDoctors.remove(selectedAppointment.getDoctor());
        }
    }

}

