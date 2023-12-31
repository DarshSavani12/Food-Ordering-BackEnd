package foodordering.foodorderingapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    @Field("email")
    private String email;
    @Field("password")
    private  String password;
}
