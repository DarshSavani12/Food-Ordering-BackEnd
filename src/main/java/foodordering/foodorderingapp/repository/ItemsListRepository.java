package foodordering.foodorderingapp.repository;

import foodordering.foodorderingapp.model.Items;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemsListRepository extends MongoRepository<Items,String> {

}
