package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor updateDoctor(Long id, Doctor doctor) {
        return doctorRepository.findById(id).map(existing -> {
            existing.setName(doctor.getName());
            existing.setService(doctor.getService());
            existing.setSpecialization(doctor.getSpecialization());
            existing.setPrice(doctor.getPrice());
            existing.setAbout(doctor.getAbout());
            existing.setPhotoUrl(doctor.getPhotoUrl());
            return doctorRepository.save(existing);
        }).orElse(null);
    }

    public boolean deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
