package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static ui.UIMenu.message;

public class Patient extends User {
    private String birthday;
    private double weight;
    private double height;
    private String blood;
    private final ArrayList<BookedAppointment> bookedAppointments = new ArrayList<>();

    public Patient(String name, String email, String address, String phoneNumber, String birthday, double weight, double height, String blood) {
        super(name, email, address, phoneNumber);
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
        this.blood = blood;
    }

    public static class BookedAppointment {
        private String ID;
        private Date date;
        private String time;
        private Doctor doctor;
        private Patient patient;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

        public BookedAppointment(Doctor.AvailableAppointment appointment, Patient patient) {
            this.ID = appointment.getID();
            this.date = appointment.getDate();
            this.time = appointment.getTime();
            this.doctor = appointment.getDoctor();
            this.patient = patient;
        }

        public String getDate(Date date) {
            return format.format(date);
        }

        public String getTime() {
            return time;
        }

        @Override
        public String toString() {
            return '[' +
                    "Date: " + getDate(date) +
                    ", Time: '" + getTime() + '\'' +
                    ", Doctor: " + doctor.getName() +
                    ", Speciality: " + doctor.getSpeciality() +
                    ", Patient: " + patient.getName() +
                    ']';
        }
    }

    public void bookAppointment(Patient.BookedAppointment bookedAppointment) {
        bookedAppointments.add(bookedAppointment);
    }


    public void showBookedAppointments() {
        if (!bookedAppointments.isEmpty()) {
            for (BookedAppointment bookedAppointment : bookedAppointments) {
                message.listItem(bookedAppointment.toString());
            }
        } else {
            message.info("EMPTY");
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
