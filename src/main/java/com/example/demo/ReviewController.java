package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Reviews> addReview(@RequestBody Reviews review) {
        Reviews saved = reviewService.saveReview(review);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<?> getReviewsByDoctor(@RequestParam Long doctorId) {
        return ResponseEntity.ok(reviewService.getReviewsByDoctorId(doctorId));
    }
}
