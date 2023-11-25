package edu.pe.upao.localboost.Repositories;

import edu.pe.upao.localboost.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
