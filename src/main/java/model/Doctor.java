package model;

import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "doctor")
public class Doctor extends User {

    @Column(name = "speciality", nullable = false)
    private String speciality;

    public Doctor() {
    }

    public Doctor(String name, String pa_surname, String ma_surname, String email, String address, String phoneNumber, String speciality) {
        super(name, pa_surname, ma_surname, email, address, phoneNumber);
        this.speciality = speciality;
    }

    @Entity(name = "AvailableAppointment")
    @Table(name = "available_appointment")
    public static class AvailableAppointment {

        @Id
        @Column(name = "id", nullable = false, length = 36)
        final private String id = UUID.randomUUID().toString();

        @Temporal(TemporalType.DATE)
        @Column(name = "date", nullable = false)
        private Date date;

        @Column(name = "time", nullable = false, length = 10)
        private String time;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "doctor_id", nullable = false)
        private Doctor doctor;

        @Transient
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

        public AvailableAppointment() {
        }

        public AvailableAppointment(String date, String time, Doctor doctor) {
            try {
                this.date = format.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            this.time = time;
            this.doctor = doctor;
        }

        public String getId() {
            return id;
        }

        public Date getDate() {
            return date;
        }

        public String getDateString() {
            return format.format(date);
        }

        public String getDate(Date date) {
            return format.format(date);
        }

        public String getTime() {
            return time;
        }

        public Doctor getDoctor() {
            return doctor;
        }

        @Override
        public String toString() {
            return "[APPOINTMENT]: "
                    + date + " | "
                    + time + " | "
                    + "Dr. " + doctor.getName() + " " + doctor.getPaSurname() + " | "
                    + "Speciality: " + doctor.getSpeciality();
        }
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
