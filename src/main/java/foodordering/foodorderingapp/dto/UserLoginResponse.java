package foodordering.foodorderingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponse {
    private String userId;
    private String email;
    private String accessToken;
}
