package foodordering.foodorderingapp.config;

import foodordering.foodorderingapp.config.filters.JwtAuthenticationFilter;
import foodordering.foodorderingapp.service.CustomAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/api/register").permitAll()
                                .requestMatchers("/api/login").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .authenticationManager(customAuthenticationManager);
        http.cors(cors -> cors.disable());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
