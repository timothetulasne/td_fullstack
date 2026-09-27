package org.polytech.spring;

import java.util.List;

public class PatientService {
    private final PatientStore store;
    
    public PatientService(PatientStore store) {
        this.store = store;
    }

    public void savePatient(Patient p) {
        store.save(p);
    }

    List<Patient> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}