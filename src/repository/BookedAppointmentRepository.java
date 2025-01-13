
package repository;

import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class BookedAppointmentRepository implements Repository<Patient.BookedAppointment> {

    Object conn;
    ArrayList<Patient.BookedAppointment> bookedAppointments = new ArrayList<>();

    public BookedAppointmentRepository(Object conn) {
        this.conn = conn;
    }

    @Override
    public List<Patient.BookedAppointment> findAll() {
        return bookedAppointments;
    }

    @Override
    public Patient.BookedAppointment findById(String id) {
        return null;
    }

    @Override
    public void save(Patient.BookedAppointment appointment) {
        bookedAppointments.add(appointment);
    }

    @Override
    public void update(Object value, String field, int id) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    public void remove(Patient.BookedAppointment appointment) {
        bookedAppointments.remove(appointment);
    }

    public ArrayList<Patient.BookedAppointment> findByPatientId(String patientId){
        ArrayList<Patient.BookedAppointment> patientBookedAppointments = new ArrayList<>();
        for (Patient.BookedAppointment appointment : bookedAppointments) {
            if (appointment.getPatient().getId().equals(patientId)) {
                patientBookedAppointments.add(appointment);
            }
        }
        return patientBookedAppointments;
    }

}
