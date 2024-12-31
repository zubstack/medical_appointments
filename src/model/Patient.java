package model;

import java.util.ArrayList;
import java.util.Date;

public class Patient extends User {
    private String birthday;
    private double weight;
    private double height;
    private String blood;
    private ArrayList<BookedAppointment> bookedAppointments = new ArrayList<>();

    public Patient(String name, String email, String address, String phoneNumber, String birthday, double weight, double height, String blood) {
        super(name, email, address, phoneNumber);
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
        this.blood = blood;
    }

    public static class BookedAppointment {
        String ID;
        Date date;
        String time;
        Doctor doctor;
        Patient patient;

        @Override
        public String toString() {
            return "BookedAppointment{" +
//                    "ID='" + ID + '\'' +
                    "Date: " + date +
                    ", Time: '" + time + '\'' +
                    ", Doctor: " + doctor.getName() +
                    ", Speciality: " + doctor.getSpeciality() +
//                    ", patient=" + patient +
                    '}';
        }

        public BookedAppointment(Doctor.AvailableAppointment appointment, Doctor doctor, Patient patient) {
            this.ID = appointment.getID();
            this.date = appointment.getDate();
            this.time = appointment.getTime();
            this.doctor = doctor;
            this.patient = patient;
        }

    }

    public void bookAppointment(Patient.BookedAppointment bookedAppointment) {
        bookedAppointments.add(bookedAppointment);
    }


    public void showBookedAppointments() {
        for (BookedAppointment bookedAppointment : bookedAppointments) {
            System.out.println(bookedAppointment);
        }
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWeight() {
        return weight + " kg.";
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height + " m.";
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    @Override
    public String toString() {
        return super.toString() + " Birthday: " + getBirthday() + " Weight: " + getWeight() + " Height: " + getHeight() + " Blood: " + getBlood();
    }
}
