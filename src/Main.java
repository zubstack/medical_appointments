import model.Auth;
import model.Nurse;
import ui.UIMenu;
import model.Doctor;
import model.Patient;

import java.util.Date;
import java.util.Scanner;

import static model.User.registerNewUser;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        UIMenu uiMenu = new UIMenu(scan);

        //   Initialize some users:
        Patient patient1 = new Patient("Gabriel", "email", "address", "092", "March 4", 70, 170.5, "C");
        registerNewUser(patient1, "gab", "gab");
        Doctor doctor1 = new Doctor("Victor", "email", "address", "098", "Family medicine");
        registerNewUser(doctor1, "vic", "vic");
        Patient patient2 = new Patient("Jessica", "email", "address", "092", "June 4", 60, 160.5, "B");
        registerNewUser(patient2, "jess", "jess");
        Doctor doctor2 = new Doctor("Ulises", "email", "address", "098", "Internal medicine");
        registerNewUser(doctor2, "uls", "uls");
        Doctor doctor3 = new Doctor("Rogelio", "email", "address", "098", "Pediatric");
        registerNewUser(doctor3, "rog", "rog");
        Nurse nurse = new Nurse("Beatriz", "email", "address", "098", "Pediatric");
        registerNewUser(nurse, "bet", "bet");

        // Login User;
        // Auth.login("vic", "vic");
        // Auth.login("gab", "gab");

        // Create initial available appointments:
        Doctor.AvailableAppointment appointment1 = new Doctor.AvailableAppointment("03/03/2025", "2pm", doctor2);
        Doctor.AvailableAppointment appointment2 = new Doctor.AvailableAppointment("03/03/2025", "4pm", doctor3);
        doctor2.addNewAppointment(appointment1);
        doctor3.addNewAppointment(appointment2);

        // Show UI Menu
        uiMenu.showMenu();
    }
}

