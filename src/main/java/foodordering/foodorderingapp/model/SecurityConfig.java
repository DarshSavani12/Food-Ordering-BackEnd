package foodordering.foodorderingapp.model;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public Cloudinary getCloudinary(){
        Map map= new HashMap();
        map.put("cloud_name","dds9bzdc3");
        map.put("api_key","194664886311462");
        map.put("api_secret","YqmjBVZx66ISrjurr7nk6Vv5GLA");
        map.put("secure",true);

        return new Cloudinary(map);
    }
}
