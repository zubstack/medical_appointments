//import java.util.Date;

import ui.UIMenu;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        UIMenu uiMenu = new UIMenu(scan);
        uiMenu.showMenu();
    }
}

/// / Creating doctor
//users.Doctor doctor = new users.Doctor("Ulises", "ul@mail", "his-home","12345", "Pediatric");
//// Creating new appointments
//users.Doctor.AvailableAppointment appointment1 = new users.Doctor.AvailableAppointment(new Date(), "2pm");
//users.Doctor.AvailableAppointment appointment2 = new users.Doctor.AvailableAppointment(new Date(), "4pm");
//// Assigning new appointments
//        doctor.seNewAppointment(appointment1);
//        doctor.seNewAppointment(appointment2);
//// Show doctor's current appointments
//        doctor.showAvailableAppointments();
//        System.out.println(doctor.getSpeciality());