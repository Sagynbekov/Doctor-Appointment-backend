package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/specializations")
public class SpecializationController {
    @Autowired
    private SpecializationService specializationService;

    @PostMapping
    public ResponseEntity<Specialization> addSpecialization(@RequestBody Specialization specialization) {
        Specialization saved = specializationService.addSpecialization(specialization);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Specialization>> getAllSpecializations() {
        return ResponseEntity.ok(specializationService.getAllSpecializations());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specialization> updateSpecialization(@PathVariable Long id, @RequestBody Specialization specialization) {
        Specialization updated = specializationService.updateSpecialization(id, specialization);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialization(@PathVariable Long id) {
        boolean deleted = specializationService.deleteSpecialization(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
