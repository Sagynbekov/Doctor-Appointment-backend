package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpecializationService {
    @Autowired
    private SpecializationRepository specializationRepository;

    public Specialization addSpecialization(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    public Specialization updateSpecialization(Long id, Specialization specialization) {
        return specializationRepository.findById(id).map(existing -> {
            existing.setName(specialization.getName());
            existing.setDescription(specialization.getDescription());
            existing.setService(specialization.getService());
            return specializationRepository.save(existing);
        }).orElse(null);
    }

    public boolean deleteSpecialization(Long id) {
        if (specializationRepository.existsById(id)) {
            specializationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
