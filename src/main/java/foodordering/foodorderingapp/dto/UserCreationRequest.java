package foodordering.foodorderingapp.dto;

import lombok.Data;

@Data
public class UserCreationRequest {
    private String email;
    private String password;
}
