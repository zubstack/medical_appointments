package repository;

import model.Patient;

import java.util.ArrayList;
import java.util.List;


public class PatientRepository implements Repository<Patient>{

    Object conn;
    private final ArrayList<Patient> patients = new ArrayList<>();

    public PatientRepository (Object conn){
        this.conn = conn;
    }

    @Override
    public List<Patient> findAll() {
        return patients;
    }

    @Override
    public Patient findById(String patientId) {
        for (Patient patient : patients) {
            if (patient.getId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public void save(Patient patient) {
        patients.add(patient);
    }

    @Override
    public void update(Object value, String field, int id) {

    }

    @Override
    public void deleteById(Integer id) {

    }

}
