import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Creating doctor
        Doctor doctor = new Doctor("Ulises", "ul@mail", "his-home","12345", "Pediatric");
        // Creating new appointments
        Doctor.AvailableAppointment appointment1 = new Doctor.AvailableAppointment(new Date(), "2pm");
        Doctor.AvailableAppointment appointment2 = new Doctor.AvailableAppointment(new Date(), "4pm");
        // Assigning new appointments
        doctor.seNewAppointment(appointment1);
        doctor.seNewAppointment(appointment2);
        // Show doctor's current appointments
        doctor.showAvailableAppointments();
        System.out.println(doctor.getSpeciality());
        // showMenu();
    }
}