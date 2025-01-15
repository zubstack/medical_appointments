package repository;

import model.Doctor;

import java.util.ArrayList;
import java.util.List;


public class DoctorRepository implements Repository<Doctor>{

    Object conn;
    private final ArrayList<Doctor> doctors = new ArrayList<>();

    public DoctorRepository (Object conn) {
        this.conn = conn;
    }

    @Override
    public List<Doctor> findAll() {
        return doctors;
    }

    @Override
    public Doctor findById(String doctorId) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(doctorId)) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public void save(Doctor doctor) {
        doctors.add(doctor);
    }

    @Override
    public void update(Object value, String field, int id) {

    }

    @Override
    public void deleteById(Integer id) {

    }

}
