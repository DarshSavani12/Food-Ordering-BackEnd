package foodordering.foodorderingapp.controller;

import foodordering.foodorderingapp.dto.UserCreationRequest;
import foodordering.foodorderingapp.dto.UserLoginRequest;
import foodordering.foodorderingapp.dto.UserLoginResponse;
import foodordering.foodorderingapp.model.User;
import foodordering.foodorderingapp.service.UserServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserCreationRequest request){
        return userService.createUser(request.getEmail(), request.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request){
        return userService.login(request.getEmail(), request.getPassword());
    }
}
