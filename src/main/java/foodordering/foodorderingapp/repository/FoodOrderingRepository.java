package foodordering.foodorderingapp.repository;

import foodordering.foodorderingapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodOrderingRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

}
