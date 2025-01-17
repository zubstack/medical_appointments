package model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "patient")

public class Patient extends User {
    @Column(name = "birthday", nullable = false)
    private String birthday;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "height", nullable = false)
    private double height;

    @Column(name = "blood", nullable = false)
    private String blood;

    public Patient() {
    }


    public Patient(String name, String pa_surname, String ma_surname, String email, String address, String phoneNumber, String birthday, double weight, double height, String blood) {
        super(name, pa_surname, ma_surname, email, address, phoneNumber);
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
        this.blood = blood;
    }

    @Entity(name = "BookedAppointment")
    @Table(name = "booked_appointment")
    public static class BookedAppointment {

        @Id
        @Column(name = "id", nullable = false, length = 36)
        private final String id = UUID.randomUUID().toString();

        @Temporal(TemporalType.DATE)
        @Column(name = "date", nullable = false)
        private Date date;

        @Column(name = "time", nullable = false, length = 10)
        private String time;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "doctor_id", nullable = false)
        private Doctor doctor;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "patient_id", nullable = false)
        private Patient patient;

        public BookedAppointment() {
        }

        public BookedAppointment(Doctor.AvailableAppointment appointment, Patient patient) {
            this.date = appointment.getDate();
            this.time = appointment.getTime();
            this.doctor = appointment.getDoctor();
            this.patient = patient;
        }

        public Patient getPatient() {
            return patient;
        }

        @Override
        public String toString() {
            return "[BOOKED]: "
                    + date + " | "
                    + time + " | "
                    + "Dr. " + doctor.getName() + " " + doctor.getPaSurname() + " | "
                    + "Speciality: " + doctor.getSpeciality() + " | "
                    + "Patient: " + patient.getName() + " " + patient.getPaSurname();
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
