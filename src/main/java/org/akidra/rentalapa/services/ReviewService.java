package org.akidra.rentalapa.services;

import org.akidra.rentalapa.entities.Review;
import org.akidra.rentalapa.entities.User;
import org.akidra.rentalapa.repositories.ReviewRepository;
import org.akidra.rentalapa.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public Review createReview(Review review) {
        // При создании отзыва проверяем рейтинг
        Review savedReview = reviewRepository.save(review);
        checkRatingAndBlockUser(savedReview.getUser().getId());
        return savedReview;
    }

    public List<Review> getReviewsByUserId(UUID userId) {
        return reviewRepository.findByUserId(userId);
    }

    public List<Review> getReviewsByRentManId(UUID rentmanId) {
        return reviewRepository.findByRentManId(rentmanId);
    }

    public Review updateReview(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(UUID id) {
        reviewRepository.deleteById(id);
    }

    public boolean checkRatingAndBlockUser(UUID userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null && user.getRating() < 2.0) {
            // Блокировка пользователя (добавить поле isBlocked в User)
            // user.setBlocked(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}