package foodordering.foodorderingapp.service;

import foodordering.foodorderingapp.model.User;
import foodordering.foodorderingapp.repository.FoodOrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private FoodOrderingRepository foodOrderingRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    User user = new User();
    public User createUser(String email, String password) {
        if (foodOrderingRepository.findByEmail(email) != null){
            throw new RuntimeException("Email Already Registered");
        }

        String hashedPassword = bCryptPasswordEncoder.encode(password);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        foodOrderingRepository.save(user);

        return user;
    }
}
