package util;

import model.Auth;
import model.Doctor;
import model.Patient;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DataBaseConnection {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Auth.class);
        configuration.addAnnotatedClass(Doctor.class);
        configuration.addAnnotatedClass(Doctor.AvailableAppointment.class);
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(Patient.BookedAppointment.class);

        return configuration.buildSessionFactory();

    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

}
