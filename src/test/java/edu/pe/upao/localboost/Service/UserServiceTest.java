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
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());

        UserService userService = new UserService(userRepository);

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        String result = userService.addUser(user);

        verify(userRepository, times(1)).save(user);

        assertEquals("Usuario registrado correctamente", result);
    }

    @Test
    void testAddUserWithExistingEmail() {
        UserService userService = new UserService(userRepository);

        User user = new User();
        user.setEmail("john.doe@example.com");

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        assertThrows(IllegalStateException.class, () -> userService.addUser(user));
    }

    @Test
    void testAddUserWithInvalidPassword() {
        UserService userService = new UserService(userRepository);

        User user = new User();
        user.setPassword("password123");

        assertThrows(IllegalStateException.class, () -> userService.addUser(user));
    }

    @Test
    void testUpdateUser() {
        UserService userService = new UserService(userRepository);

        User existingUser = new User();
        existingUser.setUserId(1L);
        existingUser.setFirstName("John");
        existingUser.setLastName("Doe");
        existingUser.setEmail("john.doe@example.com");

        User updatedUser = new User();
        updatedUser.setFirstName("Updated");
        updatedUser.setLastName("User");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        String result = userService.updateUser(updatedUser, 1L);

        verify(userRepository, times(1)).save(existingUser);

        assertEquals("Cliente actualizado correctamente", result);
    }


    @Test
    void testVerifyAccount() {
        UserService userService = new UserService(userRepository);

        User user = new User();
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        User result = userService.verifyAccount("john.doe@example.com", "password");

        assertEquals(user, result);
    }

    @Test
    void testVerifyAccountWithInvalidData() {
        UserService userService = new UserService(userRepository);

        assertThrows(IllegalStateException.class, () -> userService.verifyAccount("", ""));
    }
}
