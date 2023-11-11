package edu.pe.upao.localboost.Repositories;

import edu.pe.upao.localboost.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
}
