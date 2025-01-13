import model.Auth;
import repository.AuthRepository;
import repository.DoctorRepository;
import repository.PatientRepository;
import repository.UserRepository;
import ui.UIMenu;
import model.Doctor;
import model.Patient;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository(new Object());
        DoctorRepository doctorRepository = new DoctorRepository(new Object());
        PatientRepository patientRepository = new PatientRepository(new Object());
        AuthRepository authRepository = new AuthRepository(new Object());

        Scanner scan = new Scanner(System.in);
        UIMenu uiMenu = new UIMenu(scan, userRepository, doctorRepository, patientRepository, authRepository);

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


        // Show UI Menu
        uiMenu.showMenu();
    }
}


//   Initialize some users:
//        Patient patient1 = new Patient("Gabriel", "email", "address", "092", "March 4", 70, 170.5, "C");
//        save(patient1, "gab", "gab");
//        Doctor doctor1 = new Doctor("Victor", "email", "address", "098", "Family medicine");
//        save(doctor1, "vic", "vic");
//        Patient patient2 = new Patient("Jessica", "email", "address", "092", "June 4", 60, 160.5, "B");
//        save(patient2, "jess", "jess");
//        Doctor doctor2 = new Doctor("Ulises", "email", "address", "098", "Internal medicine");
//        save(doctor2, "uls", "uls");
//        Doctor doctor3 = new Doctor("Rogelio", "email", "address", "098", "Pediatric");
//        save(doctor3, "rog", "rog");

// Login User;
// Auth.login("vic", "vic");
// Auth.login("gab", "gab");

// Create initial available appointments:
//        Doctor.AvailableAppointment appointment1 = new Doctor.AvailableAppointment("03/03/2025", "1pm", doctor1);
//        Doctor.AvailableAppointment appointment2 = new Doctor.AvailableAppointment("03/03/2025", "2pm", doctor2);
//        Doctor.AvailableAppointment appointment3 = new Doctor.AvailableAppointment("03/03/2025", "2pm", doctor3);
//        Doctor.AvailableAppointment appointment4 = new Doctor.AvailableAppointment("03/03/2025", "4pm", doctor1);
//        Doctor.AvailableAppointment appointment5 = new Doctor.AvailableAppointment("03/03/2025", "5pm", doctor2);
//        Doctor.AvailableAppointment appointment6 = new Doctor.AvailableAppointment("03/03/2025", "6pm", doctor3);
//        doctor1.addNewAppointment(appointment1);
//        doctor2.addNewAppointment(appointment2);
//        doctor3.addNewAppointment(appointment3);
//        doctor1.addNewAppointment(appointment4);
//        doctor2.addNewAppointment(appointment5);
//        doctor3.addNewAppointment(appointment6);


