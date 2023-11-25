package edu.pe.upao.localboost.Services;

import edu.pe.upao.localboost.Dtos.ReviewDTO;
import edu.pe.upao.localboost.Models.Review;
import edu.pe.upao.localboost.Repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;


    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    private boolean isEmptyOrWhitespace(String value) {
        return value == null || value.trim().isEmpty();
    }

    public String addReview(Review review) {
        validateReview(review);
        Review savedTip = reviewRepository.save(review);
        return "Reseña registrada correctamente";
    }

    public List<ReviewDTO> getAllReview() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDTO> reviewsAll = new ArrayList<>();
        for (Review review : reviews) {
            reviewsAll.add(new ReviewDTO(review));
        }
        return reviewsAll;
    }

    private void validateReview(Review review) {
        if (isEmptyOrWhitespace(review.getText()) || isEmptyOrWhitespace(String.valueOf(review.getMarket()))
                || review.getUser() == null) {
            throw new IllegalStateException("Todos los campos son requeridos");
        }
    }
}
