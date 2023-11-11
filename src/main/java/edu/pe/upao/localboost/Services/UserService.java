package edu.pe.upao.localboost.Services;


import edu.pe.upao.localboost.Models.User;
import edu.pe.upao.localboost.Repositories.ProductRepository;
import edu.pe.upao.localboost.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;
    private ProductRepository productRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private boolean isEmptyOrWhitespace(String value) {
        return value == null || value.trim().isEmpty();
    }

    public String addUser(User user){
        List<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        user.setRegistrationDate(Instant.now());

        if (isEmptyOrWhitespace(user.getFirstName()) || isEmptyOrWhitespace(user.getLastName()) || isEmptyOrWhitespace(user.getEmail()) || user.getPassword() == null) {
            throw new IllegalStateException("Todos los campos son requeridos");
        }

        if (!existingUserByEmail.isEmpty()) {
            throw new IllegalStateException("El correo que ingresaste ya está en uso");
        }

        if (user.getPassword() != null && user.getPassword().length() > 8) {
            throw new IllegalStateException("La contraseña debe tener menos de 8 caracteres");
        }

        // Validar que el email contenga un "@"
        if (!user.getEmail().contains("@")) {
            throw new IllegalStateException("El email debe contener un '@'");
        }

        userRepository.save(user);
        return "Usuario registrado correctamente";
    }

    public String updateUser(User updatedUser, Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        if (isEmptyOrWhitespace(updatedUser.getFirstName()) || isEmptyOrWhitespace(updatedUser.getLastName())) {
            throw new IllegalStateException("Los campos 'firstName' y 'lastName' son requeridos");
        }

        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setBussinessName(updatedUser.getBussinessName());

        userRepository.save(existingUser);
        return "Cliente actualizado correctamente";
    }

    public User verifyAccount(String email, String password) {
        if (isEmptyOrWhitespace(email) || isEmptyOrWhitespace(password)) {
            throw new IllegalStateException("Correo y contraseña son campos requeridos");
        }
        List<User> existingUserByCount = userRepository.findByEmail(email);
        if (!existingUserByCount.isEmpty()) {
            User useremail = existingUserByCount.get(0);
            if (useremail.getPassword().equals(password)) {
                return useremail;
            }else{
                throw new IllegalStateException("contraseña incorrecta");
            }
        }else{
            throw new IllegalStateException("Correo y contraseña incorrectas");
        }
    }
}