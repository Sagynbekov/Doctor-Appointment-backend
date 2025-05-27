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
}
