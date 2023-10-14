package edu.pe.upao.localboost.Services;

import edu.pe.upao.localboost.Models.User;
import edu.pe.upao.localboost.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userid){
        return userRepository.findById(userid);
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public void deleteUserById(Long userid){
        userRepository.deleteById(userid);
    }

    public User verifyAccount(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Verificar si la contraseña coincide
            if (user.getPassword().equals(password)) {
                // Las credenciales son válidas
                return user;
            }
        }
        // Si no se encontró el usuario o las credenciales no coinciden, devuelve null
        return null;
    }
}