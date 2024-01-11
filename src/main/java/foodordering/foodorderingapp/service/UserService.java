package foodordering.foodorderingapp.service;

import foodordering.foodorderingapp.model.User;
import foodordering.foodorderingapp.repository.FoodOrderingRepository;
import foodordering.foodorderingapp.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private FoodOrderingRepository foodOrderingRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public User createUser(String email, String password, String username) {
        User user = new User();
        if (foodOrderingRepository.findByEmail(email) != null){
            throw new RuntimeException("Email Already Registered");
        }

        String hashedPassword = bCryptPasswordEncoder.encode(password);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setUsername(username);
        user.setRole("user");
        foodOrderingRepository.save(user);

        return user;
    }

    // Login
    public ResponseEntity<Map<String, Object>> loginUser(String email, String password){
        User user = foodOrderingRepository.findByEmail(email);
        if(user ==null){
            throw new RuntimeException("Username is not registered. Try to create first.");
        }else if(bCryptPasswordEncoder.matches(password, user.getPassword())){
            // Password match generate JWT for session token
            String sessionToken = jwtTokenProvider.generateToken(user.getId());
            // Generate refresh Token and store it
            String refreshToken = generateRefreshToken(user);
            Map<String, Object> userDetails = new HashMap<>();
            userDetails.put("userId",user.getId());
            userDetails.put("username", user.getUsername());
            userDetails.put("sessionToken",sessionToken);
            userDetails.put("refreshToken",refreshToken);
            return ResponseEntity.ok(userDetails);
        } else {
           throw new RuntimeException("Incorrect password");
        }

    }

    // Function to generate refresh Token
    private String generateRefreshToken(User user){
        String refreshToken= UUID.randomUUID().toString();
        return  refreshToken;
    }

}
