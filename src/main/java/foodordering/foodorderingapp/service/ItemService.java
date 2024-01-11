package foodordering.foodorderingapp.service;

import foodordering.foodorderingapp.model.Items;
import foodordering.foodorderingapp.repository.ItemsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ItemService {

     private ItemsListRepository itemsListRepository;
    private CloudnaryImageServiceImp cloudnaryImageServiceImp;

    @Autowired
    public ItemService(ItemsListRepository itemsListRepository, CloudnaryImageServiceImp cloudnaryImageServiceImp){
        this.itemsListRepository= itemsListRepository;
        this.cloudnaryImageServiceImp= cloudnaryImageServiceImp;
    }


    //Function which is responsible to create items
    public ResponseEntity<String> createItems(MultipartFile file, String name, String description, Integer price) {
        try {
            String imageUrl= cloudnaryImageServiceImp.uploadImage(file);
            Items item = new Items();
            item.setName(name);
            item.setDescription(description);
            item.setPrice(price);
            item.setImage(imageUrl);

            itemsListRepository.save(item);

            return ResponseEntity.ok("Item created successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating item");
        }
    }

    //Function which is responsible to get All  items
    public List getAllitems(){
        return  itemsListRepository.findAll();
    }
}
