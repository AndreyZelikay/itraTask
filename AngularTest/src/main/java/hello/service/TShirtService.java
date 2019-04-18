package hello.service;

import hello.Repos.TShirtRepo;
import hello.dao.TShirtForm;
import hello.model.TShirt;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class TShirtService {
    @Autowired
    private TShirtRepo tShirtRepo;

    public TShirt findOne(Integer id){
        return tShirtRepo.getOne(id);
    }

    public TShirt Create(TShirtForm tShirtForm){
        byte[] decodedBytes = Base64.decodeBase64(tShirtForm.getUrl());
        File photo= new File("C:/Users/andre/IdeaProjects/AngularTest/src/main/java/hello/model/","photo1.jpg");
        if (photo.exists()) {
            photo.delete();
        }
        try {
            new FileOutputStream(photo).write(decodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //tShirtForm.setUrl(cloudinaryService.post("C:/Users/andre/IdeaProjects/AngularTest/src/main/java/hello/model/photo1.jpg"));
        TShirt tShirt=new TShirt();
        tShirt.setUrl(tShirtForm.getUrl());
        tShirt.setDescription(tShirtForm.getDescription());
        tShirt.setName(tShirtForm.getName());
        tShirt.setTags(tShirtForm.getTags());
        return tShirtRepo.save(tShirt);
    }

    public List<TShirt> findAll(){
        return tShirtRepo.findAll();
    }

}
