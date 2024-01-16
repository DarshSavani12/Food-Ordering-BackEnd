package foodordering.foodorderingapp.service;

import foodordering.foodorderingapp.model.User;
import foodordering.foodorderingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomAuthenticationManager implements AuthenticationManager {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            throw new BadCredentialsException("Details not Found");
        }
        if (passwordEncoder.matches(password, user.getPassword())) {
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            grantedAuthorityList.add(new SimpleGrantedAuthority(user.getRole()));
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(email, user.getPassword(), grantedAuthorityList);
            return new UsernamePasswordAuthenticationToken(userDetails, password, grantedAuthorityList);
        }else {
            throw new BadCredentialsException("Password Incorrect");
        }
    }
}
