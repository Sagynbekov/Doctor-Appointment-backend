package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/favorits")
public class FavoritController {
    @Autowired
    private FavoritRepository favoritRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getFavoritDoctors(@RequestParam Long userId) {
        List<Favorit> favorits = favoritRepository.findByUserId(userId);
        List<Long> doctorIds = favorits.stream().map(Favorit::getDoctorId).collect(Collectors.toList());
        List<Doctor> doctors = doctorRepository.findAllById(doctorIds);
        List<Map<String, Object>> result = doctors.stream().map(doc -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", doc.getId());
            map.put("name", doc.getName());
            map.put("service", doc.getService());
            map.put("specialization", doc.getSpecialization() != null ? doc.getSpecialization().getName() : "");
            map.put("price", doc.getPrice());
            map.put("about", doc.getAbout());
            map.put("photoUrl", doc.getPhotoUrl());
            return map;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Favorit> addFavorit(@RequestBody Favorit favorit) {
        Favorit saved = favoritRepository.save(favorit);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping
    public ResponseEntity<?> removeFavorit(@RequestParam Long userId, @RequestParam Long doctorId) {
        Favorit fav = favoritRepository.findByUserIdAndDoctorId(userId, doctorId);
        if (fav != null) {
            favoritRepository.delete(fav);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
