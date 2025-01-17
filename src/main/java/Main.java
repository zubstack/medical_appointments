import repository.*;
import ui.UIMenu;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {
        // Logger config
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        UIMenu uiMenu = getUiMenu();
        uiMenu.showMenu();
    }

    private static UIMenu getUiMenu() {
        UserRepository userRepository = new UserRepository();
        DoctorRepository doctorRepository = new DoctorRepository();
        PatientRepository patientRepository = new PatientRepository();
        AuthRepository authRepository = new AuthRepository();
        AvailableAppointmentRepository availableAppointmentRepository = new AvailableAppointmentRepository();
        BookedAppointmentRepository bookedAppointmentRepository = new BookedAppointmentRepository();

        Scanner scan = new Scanner(System.in);
        return new UIMenu(scan, userRepository, doctorRepository, patientRepository, authRepository, availableAppointmentRepository, bookedAppointmentRepository);
    }
}



