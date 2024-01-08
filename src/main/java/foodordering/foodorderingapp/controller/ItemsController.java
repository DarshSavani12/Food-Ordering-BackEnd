package foodordering.foodorderingapp.controller;

import foodordering.foodorderingapp.model.Items;
import foodordering.foodorderingapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ItemsController {

   private final ItemService itemService;

   @Autowired
   public ItemsController(ItemService itemService){
       this.itemService=itemService;
   }
    @PostMapping(value = "/api/addItem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createItems(@RequestParam("image") MultipartFile file,
                                              @RequestParam("name") String name,
                                              @RequestParam("description") String description,
                                              @RequestParam("price") Integer price){

        return  itemService.createItems(file,name,description,price);
    }
}
