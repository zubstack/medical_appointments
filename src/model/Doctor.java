package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static ui.UIMenu.message;

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
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

        public AvailableAppointment(String date, String time, Doctor doctor) {
            try {
                this.date = format.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            this.time = time;
            this.doctor = doctor;
        }

        public String getID() {
            return ID;
        }

        public Date getDate() {
            return date;
        }

        public String getDate(Date date) {
            return format.format(date);
        }

        public String getTime() {
            return time;
        }

        public Doctor getDoctor(){
            return doctor;
        }

        @Override
        public String toString() {
            return "[Date: " + getDate(date) +
                    ", Time: '" + time + '\'' + ", Doctor: " + doctor.getName() + ", Speciality: " + doctor.getSpeciality() + ']';
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
        if (!availableAppointments.isEmpty()) {
            for (AvailableAppointment availableAppointment : availableAppointments) {
                message.listItem(availableAppointment.toString());
            }
        } else {
            message.info("EMPTY");
        }
    }

    public int showAvailableAppointmentsInRow(int i) {
        for (AvailableAppointment availableAppointment : availableAppointments) {
            System.out.printf("\n(%d) %s\n", i, availableAppointment.toString());
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
