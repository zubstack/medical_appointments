package repository;

import model.Patient;
import org.hibernate.Session;
import util.DataBaseConnection;

import java.util.List;


public class PatientRepository implements Repository<Patient> {
    private static final String TABLE_NAME = "patient";

    @Override
    public List<Patient> findAll() {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            List<Patient> patients = session.createSelectionQuery("from Patient", Patient.class).getResultList();
            session.getTransaction().commit();
            return patients;
        } catch (Exception e) {
            System.err.println("Error finding all " + TABLE_NAME + "s: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Patient findById(String id) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            Patient patient = session.createQuery("from Patient where id like :id", Patient.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return patient;
        } catch (Exception e) {
            System.err.println("Error finding " + TABLE_NAME + " by ID: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Patient patient) {
        try (Session session = DataBaseConnection.getSession()) { // Open and close sessions automatically.
            session.beginTransaction();
            session.persist(patient);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void update(Patient patient) {
        try (Session session = DataBaseConnection.getSession()) { // Open and close sessions automatically.
            session.beginTransaction();
            session.persist(patient);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void delete(Patient patient) {
        try (Session session = DataBaseConnection.getSession()) { // Open and close sessions automatically.
            session.beginTransaction();
            session.remove(patient);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

}
