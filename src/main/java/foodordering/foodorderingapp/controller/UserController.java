package foodordering.foodorderingapp.controller;

import foodordering.foodorderingapp.dto.UserCreationRequest;
import foodordering.foodorderingapp.model.User;
import foodordering.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://192.168.1.218:3000")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/api/users")
    public User registerUser(@RequestBody UserCreationRequest request){
        return userService.createUser(request.getEmail(), request.getPassword(), request.getUsername());
    }

    @PostMapping("/api/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserCreationRequest request){
        try {
            return userService.loginUser(request.getEmail(), request.getPassword());
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
