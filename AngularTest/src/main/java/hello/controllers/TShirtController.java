package hello.controllers;

import hello.dao.TShirtForm;
import hello.model.TShirt;
import hello.service.CloudinaryService;
import hello.service.TShirtService;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/TShirts")
@CrossOrigin
public class TShirtController {
    @Autowired
    private TShirtService tShirtService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/add")
    public TShirt create(@RequestBody TShirtForm tShirtForm){
        byte[] data=null;
        try {
            BufferedImage bImage = ImageIO.read(new File("C:/Users/andre/IdeaProjects/AngularTest/src/main/java/hello/model/image.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            data = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        return tShirtService.Create(tShirtForm);
    }

    @GetMapping("/{id}")
    public TShirt findOne(@PathVariable ( "id" ) int id){
        return tShirtService.findOne(id);
    }

    @GetMapping("/all")
    public List<TShirt> findAll(){
        return tShirtService.findAll();
    }
    @GetMapping("/last")
    public TShirt getLast(){
        return tShirtService.findOne(tShirtService.findAll().size());
    }
}