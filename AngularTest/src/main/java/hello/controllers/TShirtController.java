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