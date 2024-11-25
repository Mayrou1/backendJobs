package apis.com.controller;
 
 
import java.util.List;

import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

import apis.com.config.JwtService; 
import apis.com.dto.UserDto;
import apis.com.entities.User; 
import apis.com.service.UserService; 

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
 
    private final UserService userService;
    
    
    public UserController(JwtService jwtService, UserService userService) { 
        this.userService = userService;
    } 
    
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Integer id,
            @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}