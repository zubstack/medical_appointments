package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Doctor extends User {
    private String speciality;

    public Doctor(String name, String email, String address, String phoneNumber, String speciality) {
        super(name, email, address, phoneNumber);
        this.speciality = speciality;
    }

    public static class AvailableAppointment {

        final private String ID = UUID.randomUUID().toString();
        private final Date date;
        private final String time;
        private final Doctor doctor;
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
