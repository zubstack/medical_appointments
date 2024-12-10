//import java.util.Date;

import ui.UIMenu;
import users.Doctor;
import users.Patient;
import users.User;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        UIMenu uiMenu = new UIMenu(scan);

        //   Initialize some users:
        Patient patient1 = new Patient("Gabriel", "email", "address", "092", "March 4", 70, 170.5, "C");
        User.registerNewUser(patient1, "gab", "gab");
        Doctor doctor1 = new Doctor("Victor", "email", "address", "098", "Pediatric");
        User.registerNewUser(doctor1, "vic", "vic");
        Patient patient2 = new Patient("Jessica", "email", "address", "092", "June 4", 60, 160.5, "B");
        User.registerNewUser(patient2, "jess", "jess");
        Doctor doctor2 = new Doctor("Ulises", "email", "address", "098", "Pediatric");
        User.registerNewUser(doctor2, "uls", "uls");

        // Show UI Menu
        uiMenu.showMenu();
    }
}

/// / Creating doctor
//users.Doctor doctor = new users.Doctor("Ulises", "ul@mail", "his-home","12345", "Pediatric");
/// / Creating new appointments
//users.Doctor.AvailableAppointment appointment1 = new users.Doctor.AvailableAppointment(new Date(), "2pm");
//users.Doctor.AvailableAppointment appointment2 = new users.Doctor.AvailableAppointment(new Date(), "4pm");
//// Assigning new appointments
//        doctor.seNewAppointment(appointment1);
//        doctor.seNewAppointment(appointment2);
//// Show doctor's current appointments
//        doctor.showAvailableAppointments();
//        System.out.println(doctor.getSpeciality());