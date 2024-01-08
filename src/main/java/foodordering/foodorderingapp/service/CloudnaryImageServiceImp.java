package foodordering.foodorderingapp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
@Service
public class CloudnaryImageServiceImp {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudnaryImageServiceImp(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) {
        try {
            // Upload the image to Cloudinary
                        Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            // Extract the public URL of the uploaded image
            return (String) result.get("secure_url");
        } catch (IOException e) {
            System.out.println("erroor is throwing");
            e.printStackTrace(); // Handle the exception appropriately
            return null;
        }
    }
}
