package model;

public class Patient extends User {
    private String birthday;
    private double weight;
    private double height;
    private String blood;

    public Patient(String name, String email, String address, String phoneNumber, String birthday, double weight, double height, String blood) {
        super(name, email, address, phoneNumber);
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
        this.blood = blood;
    }

    public static class BookedAppointment extends Doctor.AvailableAppointment {
        private final String ID;
        private final Patient patient;

        public BookedAppointment(Doctor.AvailableAppointment appointment, Patient patient) {
            super(appointment.getDate(), appointment.getTime(), appointment.getDoctor());
            this.patient = patient;
            this.ID = appointment.getID();
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
