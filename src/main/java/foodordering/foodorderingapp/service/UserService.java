package foodordering.foodorderingapp.service;

import foodordering.foodorderingapp.model.User;
import foodordering.foodorderingapp.repository.FoodOrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private FoodOrderingRepository foodOrderingRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(String email, String password) {
        User user = new User();
        if (foodOrderingRepository.findByEmail(email) != null){
            throw new RuntimeException("Email Already Registered");
        }

        String hashedPassword = bCryptPasswordEncoder.encode(password);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setRole("user");
        foodOrderingRepository.save(user);

        return user;
    }

    // Login
    public ResponseEntity<String> loginUser(String email, String password){
        User user = foodOrderingRepository.findByEmail(email);
        if(user ==null){
            throw new RuntimeException("Username is not registered. Try to create first.");
        }else if(bCryptPasswordEncoder.matches(password, user.getPassword())){
            return  ResponseEntity.ok("Successfully logged in");
        } else {
           throw new RuntimeException("Incorrect password");
        }

    }
}
