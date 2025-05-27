package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Reviews saveReview(Reviews review) {
        return reviewRepository.save(review);
    }

    public List<Reviews> getReviewsByDoctorId(Long doctorId) {
        return reviewRepository.findByDoctorId(doctorId);
    }
}
