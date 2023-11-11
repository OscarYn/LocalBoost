package edu.pe.upao.localboost.Controllers;

import edu.pe.upao.localboost.Models.Coment;
import edu.pe.upao.localboost.Models.User;
import edu.pe.upao.localboost.Services.UserService;
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

    @GetMapping
    private List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userid}")
    public User getUserById(@PathVariable Long userid){
        return userService.getUserById(userid).orElse(new User());
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping("/{userid}")
    public void addUser(@RequestBody User user, @PathVariable Long userid){
        userService.updateUser(user, userid);
    }

    @DeleteMapping("/{userid}")
    public void deleteUser(@PathVariable Long userid){
        userService.deleteUserById(userid);
    }

    @PostMapping("/login")
    public ResponseEntity<Coment> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        User user = userService.verifyAccount(email, password);

        if (user != null) {
            String comment = "Estas de vuelta: " + user.getFirstName() + user.getLastName();
            Coment res = new Coment(comment, user);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}