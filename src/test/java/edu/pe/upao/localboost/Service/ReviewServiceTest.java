package edu.pe.upao.localboost.Service;

import edu.pe.upao.localboost.Dtos.ReviewDTO;
import edu.pe.upao.localboost.Models.Market;
import edu.pe.upao.localboost.Models.Review;
import edu.pe.upao.localboost.Models.User;
import edu.pe.upao.localboost.Repositories.ReviewRepository;
import edu.pe.upao.localboost.Services.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void testAddReview() {
        // Create a sample review for testing
        Review review = new Review();
        review.setText("Sample review text");
        review.setMarket(null); // You may need to set a valid market for your test
        review.setUser(new User()); // You may need to set a valid user for your test

        // Mock the behavior of the reviewRepository.save() method
        when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(review);

        // Perform the service method
        String result = reviewService.addReview(review);

        // Assert the result
        assertEquals("Reseña registrada correctamente", result);
    }

    @Test
    void testGetAllReview() {
        List<Review> reviewList = new ArrayList<>();
        when(reviewRepository.findAll()).thenReturn(reviewList);
        List<ReviewDTO> result = reviewService.getAllReview();
        verify(reviewRepository, times(1)).findAll();
    }
}