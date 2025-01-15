package repository;

import model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class AvailableAppointmentRepository implements Repository<Doctor.AvailableAppointment> {

    Object conn;
    ArrayList<Doctor.AvailableAppointment> availableAppointments = new ArrayList<>();

    public AvailableAppointmentRepository(Object conn) {
        this.conn = conn;
    }

    @Override
    public List<Doctor.AvailableAppointment> findAll() {
        return availableAppointments;
    }

    @Override
    public Doctor.AvailableAppointment findById(String id) {
        return null;
    }

    @Override
    public void save(Doctor.AvailableAppointment appointment) {
        availableAppointments.add(appointment);
    }

    @Override
    public void update(Object value, String field, int id) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    public void remove(Doctor.AvailableAppointment appointment) {
        availableAppointments.remove(appointment);
    }

    public ArrayList<Doctor.AvailableAppointment> findByDoctorId(String doctorId){
        ArrayList<Doctor.AvailableAppointment> doctorAvailableAppointments = new ArrayList<>();
        for (Doctor.AvailableAppointment appointment : availableAppointments) {
            if (appointment.getDoctor().getId().equals(doctorId)) {
                doctorAvailableAppointments.add(appointment);
            }
        }
        return doctorAvailableAppointments;
    }

}
