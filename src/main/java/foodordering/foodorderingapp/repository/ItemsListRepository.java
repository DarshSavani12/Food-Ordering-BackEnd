package foodordering.foodorderingapp.repository;

import foodordering.foodorderingapp.model.Items;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemsListRepository extends MongoRepository<Items,String> {
    @Override
    List<Items> findAll();

}
