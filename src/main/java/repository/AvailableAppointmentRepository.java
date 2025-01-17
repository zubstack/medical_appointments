package repository;

import model.Doctor.AvailableAppointment;
import model.Doctor;
import org.hibernate.Session;
import util.DataBaseConnection;

import java.util.Collections;
import java.util.List;

public class AvailableAppointmentRepository implements Repository<Doctor.AvailableAppointment> {

    private static final String TABLE_NAME =  "available_appointment";

    @Override
    public List<Doctor.AvailableAppointment> findAll() {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            List<AvailableAppointment> appointments = session.createSelectionQuery(
                    "from AvailableAppointment",
                    Doctor.AvailableAppointment.class
            ).getResultList();
            session.getTransaction().commit();
            return appointments;
        } catch (Exception e) {
            System.err.println("Error finding all " + TABLE_NAME + "s: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Doctor.AvailableAppointment findById(String id) {
        return null;
    }

    @Override
    public void save(Doctor.AvailableAppointment appointment) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            session.persist(appointment);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void update(Doctor.AvailableAppointment appointment) {
        try (Session session = DataBaseConnection.getSession()) { // Open and close sessions automatically.
            session.beginTransaction();
            session.persist(appointment);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void delete(Doctor.AvailableAppointment appointment) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            session.remove(appointment);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    public List<Doctor.AvailableAppointment> findByDoctorId(String doctorId) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            List<Doctor.AvailableAppointment> appointments = session.createQuery(
                    "from AvailableAppointment where doctor.id = :doctorId",
                    Doctor.AvailableAppointment.class
            ).setParameter("doctorId", doctorId).getResultList();
            session.getTransaction().commit();
            return appointments;
        } catch (Exception e) {
            return null;
        }
    }

}
