package repository;

import model.Auth;
import model.Doctor;
import model.Doctor;
import org.hibernate.Session;
import util.DataBaseConnection;

import java.util.List;


public class DoctorRepository implements Repository<Doctor> {

    private static final String TABLE_NAME =  "doctor";

    @Override
    public List<Doctor> findAll() {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            List<Doctor> doctors = session.createSelectionQuery("from Doctor", Doctor.class).getResultList();
            session.getTransaction().commit();
            return doctors;
        } catch (Exception e) {
            System.err.println("Error finding all " + TABLE_NAME + "s: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Doctor findById(String id) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            Doctor doctor = session.createQuery("from Doctor where id like :id", Doctor.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return doctor;
        } catch (Exception e) {
            System.err.println("Error finding " + TABLE_NAME + " by ID: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Doctor doctor) {
        try (Session session = DataBaseConnection.getSession()) { // Open and close sessions automatically.
            session.beginTransaction();
            session.persist(doctor);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void update(Doctor doctor) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            session.persist(doctor);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void delete(Doctor doctor) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            session.remove(doctor);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

}
