package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoritRepository extends JpaRepository<Favorit, Long> {
    List<Favorit> findByUserId(Long userId);
    Favorit findByUserIdAndDoctorId(Long userId, Long doctorId);
}
