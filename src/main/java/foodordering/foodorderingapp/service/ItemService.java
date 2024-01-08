package foodordering.foodorderingapp.service;

import foodordering.foodorderingapp.model.Items;
import foodordering.foodorderingapp.repository.ItemsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemService {

     private ItemsListRepository itemsListRepository;
    private CloudnaryImageServiceImp cloudnaryImageServiceImp;

    @Autowired
    public ItemService(ItemsListRepository itemsListRepository, CloudnaryImageServiceImp cloudnaryImageServiceImp){
        this.itemsListRepository= itemsListRepository;
        this.cloudnaryImageServiceImp= cloudnaryImageServiceImp;
    }


    //method which is responsiable to create items
    public ResponseEntity<String> createItems(MultipartFile file, String name, String description, Integer price) {
        try {
            // Validate and process the file
            // Save the file to your storage or cloud service (e.g., Cloudinary)

            // Create an Items object with the provided details
            String imageUrl= cloudnaryImageServiceImp.uploadImage(file);
            Items item = new Items();
            item.setName(name);
            item.setDescription(description);
            item.setPrice(price);
            item.setImage(imageUrl);

            // Set the image field with the processed MultipartFile
            // Save the file path or URL in your Items object
            // item.setImagePath("path/to/your/uploaded/image");

            // Save the Items object to your database
            itemsListRepository.save(item);

            return ResponseEntity.ok("Item created successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating item");
        }
    }
}
