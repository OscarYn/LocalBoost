package edu.pe.upao.localboost.Controllers;

import edu.pe.upao.localboost.Dtos.UserDTO;
import edu.pe.upao.localboost.Models.Product;
import edu.pe.upao.localboost.Models.User;
import edu.pe.upao.localboost.Repositories.ProductRepository;
import edu.pe.upao.localboost.Repositories.UserRepository;
import edu.pe.upao.localboost.Services.UserService;
import edu.pe.upao.localboost.mappers.LoginRequest;
import edu.pe.upao.localboost.mappers.LoginResponse;
import edu.pe.upao.localboost.util.EncryptionUtil;
import edu.pe.upao.localboost.util.JwtTokenUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

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
    LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception{
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if(user.isPresent()){
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
                return new LoginResponse(EncryptionUtil.encrypt(jwtTokenUtil.generateToken(user.get())));
            }catch (AuthenticationException e){
                //pass to the throw.
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo y/o contraseña incorrecta");
    }


}