package edu.pe.upao.localboost.Controllers;

import edu.pe.upao.localboost.Dtos.UserDTO;
import edu.pe.upao.localboost.Models.Product;
import edu.pe.upao.localboost.Models.User;
import edu.pe.upao.localboost.Repositories.ProductRepository;
import edu.pe.upao.localboost.Services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            String newUser = userService.addUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (IllegalStateException sms){
            return new ResponseEntity<>(sms.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@RequestBody User updatedUser, @PathVariable Long userId) {
        try {
            String message = userService.updateUser(updatedUser, userId);
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
        try {
            String email = loginRequest.get("email");
            String password = loginRequest.get("password");
            User loginuser = userService.verifyAccount(email, password);
            return new ResponseEntity<>(loginuser, HttpStatus.OK);
        }catch (IllegalStateException sms){
            return new ResponseEntity<>(sms.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}