package edu.pe.upao.localboost.Controllers;

import edu.pe.upao.localboost.Dtos.MarketDTO;
import edu.pe.upao.localboost.Dtos.ReviewDTO;
import edu.pe.upao.localboost.Models.Market;
import edu.pe.upao.localboost.Models.Review;
import edu.pe.upao.localboost.Models.User;
import edu.pe.upao.localboost.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody Review review){
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String newTip = reviewService.addReview(review);
            return new ResponseEntity<>(newTip, HttpStatus.CREATED);
        } catch (IllegalStateException sms){
            return new ResponseEntity<>(sms.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/viewreview")
    public List<ReviewDTO> getAllReview() {
        return reviewService.getAllReview();
    }
}
