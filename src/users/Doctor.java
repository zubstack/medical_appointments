package users;

import java.util.ArrayList;
import java.util.Date;

public class Doctor extends User {
    public static int id = 0;
    private String speciality;

    ArrayList<AvailableAppointment> availableAppointments = new ArrayList<>();
    static ArrayList <Doctor> doctors = new ArrayList<>();

    public Doctor(String name, String email, String address, String phoneNumber, String speciality) {
        super(name, email, address, phoneNumber);
        this.speciality = speciality;
        id++;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    static public void registerDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    static public void showDoctors(){
        for (Doctor doctor : doctors) {
            System.out.println("Doctor: " + doctor.getName());
        }
    }

    public void seNewAppointment(AvailableAppointment appointment) {
        availableAppointments.add(appointment);
    }

    public void showAvailableAppointments() {
        System.out.println("Available appointments");
        for (AvailableAppointment availableAppointment : availableAppointments) {
            System.out.println(availableAppointment.getDate() + " " + availableAppointment.getTime());
        }
    }

    public static class AvailableAppointment {
        int id;
        Date date;
        String time;

        public AvailableAppointment(Date date, String time) {
            this.date = date;
            this.time = time;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
