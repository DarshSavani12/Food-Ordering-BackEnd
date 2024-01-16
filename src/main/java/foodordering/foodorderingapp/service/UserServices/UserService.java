package foodordering.foodorderingapp.service.UserServices;

import foodordering.foodorderingapp.dto.UserLoginResponse;
import foodordering.foodorderingapp.model.User;
import foodordering.foodorderingapp.repository.UserRepository;
import foodordering.foodorderingapp.service.CustomAuthenticationManager;
import foodordering.foodorderingapp.service.JwtServices.JwtProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProviderService jwtProviderService;
    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Transactional
    public ResponseEntity<User> createUser(String email, String password) {
        User user = new User();
        if (userRepository.findByEmail(email) != null){
            throw new RuntimeException("Email Already Registered");
        }

        String hashedPassword = passwordEncoder.encode(password);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @Transactional
    public ResponseEntity<UserLoginResponse> login(String email, String password) {
        try {
            Authentication authentication =
                    customAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            User user = userRepository.findByEmail(email).orElse(null);
            org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

            String token = jwtProviderService.generateToken(principal);
            return ResponseEntity.ok(new UserLoginResponse(user.getUserId(), user.getEmail(), token));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Login Failed");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not Found"));

        return new org.springframework.security.core.userdetails.User(email,
                user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole())));
    }
}
