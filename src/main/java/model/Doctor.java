package model;

import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "doctor")
public class Doctor extends User {

    @Column(name = "speciality", nullable = false, length = 255)
    private String speciality;

    protected Doctor() {}

    public Doctor(String name, String email, String address, String phoneNumber, String speciality) {
        super(name, email, address, phoneNumber);
        this.speciality = speciality;
    }

    @Entity
    @Inheritance(strategy = InheritanceType.JOINED)
    @Table(name = "available_appointment")
    public static class AvailableAppointment {

        @Id
        @Column(name = "id", nullable = false, length = 36)
        final private String id = UUID.randomUUID().toString();

        @Temporal(TemporalType.DATE)
        @Column(name = "appointment_date", nullable = false)
        private final Date date;

        @Column(name = "appointment_time", nullable = false, length = 10)
        private final String time;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "doctor_id", nullable = false)
        private final Doctor doctor;

        @Transient
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

        public String getId() {
            return id;
        }

        public String getDate() {
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
            return "[APPOINTMENT] : Date: " + getDate() +
                    ", Time: '" + time + '\'' + ", Doctor: " + doctor.getName() + ", Speciality: " + doctor.getSpeciality() ;
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
