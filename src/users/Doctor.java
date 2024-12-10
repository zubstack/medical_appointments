package users;

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

        public AvailableAppointment(Date date, String time) {
            this.date = date;
            this.time = time;
        }

        @Override
        public String toString() {
            return "AvailableAppointment{" +
                    "date=" + date +
                    ", time='" + time + '\'' +
                    '}';
        }
    }

    public void addNewAppointment(AvailableAppointment appointment) {
        availableAppointments.add(appointment);
    }

    public void showAvailableAppointments() {
        System.out.println("Available appointments");
        for (AvailableAppointment availableAppointment : availableAppointments) {
            System.out.println(availableAppointment.toString());
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
        return super.toString() +  " Speciality: " + getSpeciality();
    }
}
