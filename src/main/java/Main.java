import model.Auth;
import org.hibernate.Session;
import repository.*;
import ui.UIMenu;
import model.Doctor;
import model.Patient;
import util.DataBaseConnection;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Session session = DataBaseConnection.getSession();

        UserRepository userRepository = new UserRepository(new Object());
        DoctorRepository doctorRepository = new DoctorRepository(new Object());
        PatientRepository patientRepository = new PatientRepository(new Object());
        AuthRepository authRepository = new AuthRepository(new Object());
        AvailableAppointmentRepository availableAppointmentRepository = new AvailableAppointmentRepository(new Object());        BookedAppointmentRepository bookedAppointmentRepository = new BookedAppointmentRepository(new Object());

        Scanner scan = new Scanner(System.in);
        UIMenu uiMenu = new UIMenu(scan, userRepository, doctorRepository, patientRepository, authRepository, availableAppointmentRepository, bookedAppointmentRepository);

        // Populate some users:

        Patient patient1 = new Patient("Gabriel", "email", "address", "092", "March 4", 70, 170.5, "C");
        Doctor doctor1 = new Doctor("Victor", "email", "address", "098", "Family medicine");

        Auth auth1 = new Auth("vic", doctor1.getId(), "vic");
        Auth auth2 = new Auth("gab", patient1.getId(), "gab");

        userRepository.save(doctor1);
        doctorRepository.save(doctor1);
        authRepository.save(auth1);

        userRepository.save(patient1);
        patientRepository.save(patient1);
        authRepository.save(auth2);

        // Appointments:

        Doctor.AvailableAppointment appointment1 =  new Doctor.AvailableAppointment("03/03/03", "1pm", doctor1);
        availableAppointmentRepository.save(appointment1);

//        Patient.BookedAppointment booked1 =  new Patient.BookedAppointment(appointment1, patient1);
//        bookedAppointmentRepository.save(booked1);
//        availableAppointmentRepository.remove(appointment1);

        // Show UI Menu
        uiMenu.showMenu();
    }
}



