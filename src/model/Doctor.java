package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Doctor extends User {
    private String speciality;
    ArrayList<AvailableAppointment> availableAppointments = new ArrayList<>();

    public Doctor(String name, String email, String address, String phoneNumber, String speciality) {
        super(name, email, address, phoneNumber);
        this.speciality = speciality;
    }

    public static class AvailableAppointment {

        final private String ID = UUID.randomUUID().toString();
        Date date;
        String time;
        Doctor doctor;

        public AvailableAppointment(Date date, String time, Doctor doctor) {
            this.date = date;
            this.time = time;
            this.doctor = doctor;
        }

        public String getID() {
            return ID;
        }

        public Date getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        @Override
        public String toString() {
            return "Date:" + date +
                    ", Time: '" + time + '\'' + ", Doctor: " + doctor.getName() + ", Speciality: " + doctor.getSpeciality();
        }
    }

    public ArrayList<Doctor.AvailableAppointment> getAvailableAppointments() {
        return availableAppointments;
    }

    public void removeAvailableAAppointment(Doctor.AvailableAppointment appointment) {
        availableAppointments.remove(appointment);
    }

    public boolean hasAvailableAppointments() {
        return !this.availableAppointments.isEmpty();
    }

    public void addNewAppointment(AvailableAppointment appointment) {
        availableAppointments.add(appointment);
    }

    public void showAvailableAppointments() {
        System.out.println("Available appointments");
        for (AvailableAppointment availableAppointment : availableAppointments) {
            System.out.println("- " + availableAppointment.toString());
        }
    }

    public int showAvailableAppointmentsInRow(int i) {
        for (AvailableAppointment availableAppointment : availableAppointments) {
            System.out.printf("\n[%d] %s\n", i, availableAppointment.toString());
            i++;
        }
        return i;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return super.toString() + " Speciality: " + getSpeciality();
    }
}
