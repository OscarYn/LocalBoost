package edu.pe.upao.localboost.Service;
import edu.pe.upao.localboost.Models.User;
import edu.pe.upao.localboost.Repositories.UserRepository;
import edu.pe.upao.localboost.Services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testAddUser() {
        // Asegúrate de que el objeto User esté inicializado correctamente
        User user = new User(null, "John", "Doe", "john@example.com", "password", null, null, Instant.now());

        // Asegúrate de que el método findByEmail devuelva algo
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Collections.emptyList());

        String result = userService.addUser(user);

        verify(userRepository, times(1)).save(user);
        assertEquals("Usuario registrado correctamente", result);
    }

    @Test
    void testAddUser_ExistingEmail() {
        // Asegúrate de que el objeto User esté inicializado correctamente
        User user = new User(null, "John", "Doe", "john@example.com", "password", null, null, Instant.now());

        // Asegúrate de que el método findByEmail devuelva algo
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Collections.singletonList(user));

        assertThrows(IllegalStateException.class, () -> userService.addUser(user));
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;
        // Asegúrate de que el objeto existingUser esté inicializado correctamente
        User existingUser = new User(userId, "John", "Doe", "john@example.com", "password", null, null, Instant.now());
        // Asegúrate de que el objeto updatedUser esté inicializado correctamente
        User updatedUser = new User(null, "Updated", "User", "updated@example.com", "newpassword", null, null, Instant.now());

        // Asegúrate de que el método findById devuelva algo
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        String result = userService.updateUser(updatedUser, userId);

        verify(userRepository, times(1)).save(existingUser);
        assertEquals("Cliente actualizado correctamente", result);
    }

    @Test
    void testUpdateUser_UserNotFound() {
        Long userId = 1L;
        // Asegúrate de que el objeto updatedUser esté inicializado correctamente
        User updatedUser = new User(null, "Updated", "User", "updated@example.com", "newpassword", null, null, Instant.now());

        // Asegúrate de que el método findById devuelva Optional.empty()
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(updatedUser, userId));
    }

    @Test
    void testVerifyAccount() {
        String email = "john@example.com";
        String password = "password";
        // Asegúrate de que el objeto user esté inicializado correctamente
        User user = new User(null, "John", "Doe", email, password, null, null, Instant.now());

        // Asegúrate de que el método findByEmail devuelva algo
        when(userRepository.findByEmail(email)).thenReturn(Collections.singletonList(user));

        User result = userService.verifyAccount(email, password);

        assertEquals(user, result);
    }

    @Test
    void testVerifyAccount_IncorrectPassword() {
        String email = "john@example.com";
        String password = "incorrectpassword";
        // Asegúrate de que el objeto user esté inicializado correctamente
        User user = new User(null, "John", "Doe", email, "password", null, null, Instant.now());

        // Asegúrate de que el método findByEmail devuelva algo
        when(userRepository.findByEmail(email)).thenReturn(Collections.singletonList(user));

        assertThrows(IllegalStateException.class, () -> userService.verifyAccount(email, password));
    }

    @Test
    void testVerifyAccount_IncorrectEmail() {
        String email = "nonexistent@example.com";
        String password = "password";

        // Asegúrate de que el método findByEmail devuelva Optional.empty()
        when(userRepository.findByEmail(email)).thenReturn(Collections.emptyList());

        assertThrows(IllegalStateException.class, () -> userService.verifyAccount(email, password));
    }
}
