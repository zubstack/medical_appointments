package repository;

import model.Auth;
import org.hibernate.Session;
import util.DataBaseConnection;

import java.util.List;

public class AuthRepository implements Repository<Auth> {

    private static final String TABLE_NAME =  "auth";

    @Override
    public List<Auth> findAll() {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            List<Auth> auths = session.createSelectionQuery("from Auth", Auth.class).getResultList();
            session.getTransaction().commit();
            return auths;
        } catch (Exception e) {
            System.err.println("Error finding all " + TABLE_NAME + "s: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Auth findById(String id) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            Auth auth = session.createQuery("from Auth where id like :id", Auth.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return auth;
        } catch (Exception e) {
            System.err.println("Error finding " + TABLE_NAME + " by ID: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Auth auth) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            session.persist(auth);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void update(Auth auth) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            session.persist(auth);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error updating " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void delete(Auth auth) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            session.remove(auth);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error deleting " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    public Auth findByUsername(String username) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            Auth auth = session.createQuery("from Auth where username like :username", Auth.class)
                    .setParameter("username", username)
                    .getSingleResult();
            session.getTransaction().commit();
            return auth;
        } catch (Exception e) {
           return null;
        }
    }
}
