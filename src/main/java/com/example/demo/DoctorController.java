package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor saved = doctorService.addDoctor(doctor);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        Doctor updated = doctorService.updateDoctor(id, doctor);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        boolean deleted = doctorService.deleteDoctor(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
