
package repository;

import model.Doctor;
import model.Patient;
import org.hibernate.Session;
import util.DataBaseConnection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookedAppointmentRepository implements Repository<Patient.BookedAppointment> {

    private static final String TABLE_NAME = "booked_appointment";

    @Override
    public List<Patient.BookedAppointment> findAll() {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            List<Patient.BookedAppointment> appointments = session.createSelectionQuery(
                    "from BookedAppointment",
                    Patient.BookedAppointment.class
            ).getResultList();
            session.getTransaction().commit();
            return appointments;
        } catch (Exception e) {
            System.err.println("Error finding all " + TABLE_NAME + "s: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Patient.BookedAppointment findById(String id) {
        return null;
    }

    @Override
    public void save(Patient.BookedAppointment appointment) {
        try (Session session = DataBaseConnection.getSession()) { // Open and close sessions automatically.
            session.beginTransaction();
            session.persist(appointment);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void update(Patient.BookedAppointment appointment) {
        try (Session session = DataBaseConnection.getSession()) { // Open and close sessions automatically.
            session.beginTransaction();
            session.persist(appointment);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void delete(Patient.BookedAppointment appointment) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            session.remove(appointment);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    public List<Patient.BookedAppointment> findByPatientId(String patientId) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            List<Patient.BookedAppointment> appointments = session.createQuery(
                    "from BookedAppointment where patient.id = :patientId",
                    Patient.BookedAppointment.class
            ).setParameter("patientId", patientId).getResultList();
            session.getTransaction().commit();
            return appointments;
        } catch (Exception e) {
            return null;
        }
    }

}
