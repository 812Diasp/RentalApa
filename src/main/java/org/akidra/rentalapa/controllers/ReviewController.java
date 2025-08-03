package org.akidra.rentalapa.controllers;

import org.akidra.rentalapa.entities.Review;
import org.akidra.rentalapa.services.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public Review create(@RequestBody Review review) {
        return reviewService.createReview(review);
    }

    @GetMapping("/user/{userId}")
    public List<Review> getByUser(@PathVariable UUID userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @GetMapping("/rentman/{rentmanId}")
    public List<Review> getByRentMan(@PathVariable UUID rentmanId) {
        return reviewService.getReviewsByRentManId(rentmanId);
    }

    @PutMapping("/{id}")
    public Review update(@PathVariable UUID id, @RequestBody Review review) {
        review.setId(id);
        return reviewService.updateReview(review);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        reviewService.deleteReview(id);
    }

    @PostMapping("/{id}/block-check")
    public boolean checkAndBlockUser(@PathVariable UUID id) {
        return reviewService.checkRatingAndBlockUser(id);
    }
}