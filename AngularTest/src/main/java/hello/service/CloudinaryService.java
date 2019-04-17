package hello.service;
import com.cloudinary.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.cloudinary.Cloudinary.asMap;
import static com.cloudinary.Cloudinary.emptyMap;

@Service
public class CloudinaryService {
    public String post(String pathName){
        Cloudinary cloudinary = new Cloudinary(asMap(
                "cloud_name", "dafn1lnfo",
                "api_key", "114772972964846",
                "api_secret", "RKSMbaHNIzU9HdGERUtQHyKG1C4"));
        File toUpload = new File(pathName);
        String str="";
        try {
            Map uploadResult = cloudinary.uploader().upload(toUpload, emptyMap());
            str = uploadResult.get("url").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
