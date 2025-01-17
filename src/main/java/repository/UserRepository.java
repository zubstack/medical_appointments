package repository;

import model.User;
import org.hibernate.Session;
import util.DataBaseConnection;

import java.util.List;

public class UserRepository implements Repository<User> {
    private static final String TABLE_NAME =  "user";

    @Override
    public List<User> findAll() {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            List<User> users = session.createSelectionQuery("from User", User.class).getResultList();
            session.getTransaction().commit();
            return users;
        } catch (Exception e) {
            System.err.println("Error finding all " + TABLE_NAME + "s: " + e.getMessage());
            return null;
        }
    }

    @Override
    public User findById(String id) {
        try (Session session = DataBaseConnection.getSession()) {
            session.beginTransaction();
            User user = session.createQuery("from User where id like :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            System.err.println("Error finding " + TABLE_NAME + " by ID: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void save(User user) {
        try (Session session = DataBaseConnection.getSession()) { // Open and close sessions automatically.
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try (Session session = DataBaseConnection.getSession()) { // Open and close sessions automatically.
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        try (Session session = DataBaseConnection.getSession()) { // Open and close sessions automatically.
            session.beginTransaction();
            session.remove(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error saving " + TABLE_NAME + ": " + e.getMessage());
        }
    }
}
