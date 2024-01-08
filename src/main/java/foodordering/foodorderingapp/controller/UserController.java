package foodordering.foodorderingapp.controller;

import foodordering.foodorderingapp.dto.UserCreationRequest;
import foodordering.foodorderingapp.model.User;
import foodordering.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://192.168.1.218:3000")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/api/users")
    public User registerUser(@RequestBody UserCreationRequest request){
        return userService.createUser(request.getEmail(), request.getPassword());
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> loginUser(@RequestBody UserCreationRequest request){
        return userService.loginUser(request.getEmail(),request.getPassword());
    }
}
