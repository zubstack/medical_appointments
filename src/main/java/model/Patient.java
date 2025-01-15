package model;

import jakarta.persistence.*;

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

    public Patient (){};

    public Patient(String name, String email, String address, String phoneNumber, String birthday, double weight, double height, String blood) {
        super(name, email, address, phoneNumber);
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
        this.blood = blood;
    }

    @Entity
    @Table(name = "booked_appointment")
    public static class BookedAppointment extends Doctor.AvailableAppointment {

        @Id
        @Column(name = "id", nullable = false, length = 36)
        private final String id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "patient_id", nullable = false)
        private final Patient patient;

        public BookedAppointment(Doctor.AvailableAppointment appointment, Patient patient) {
            super(appointment.getDate(), appointment.getTime(), appointment.getDoctor());
            this.patient = patient;
            this.id = appointment.getId();
        }

        public Patient getPatient() {
            return patient;
        }

        @Override
        public String toString() {
            return "[BOOKED]" +
                    "Date: " + super.getDate() +
                    ", Time: '" + super.getTime() + '\'' +
                    ", Doctor: " + super.getDoctor().getName() +
                    ", Speciality: " + super.getDoctor().getSpeciality() +
                    ", Patient: " + patient.getName();
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
