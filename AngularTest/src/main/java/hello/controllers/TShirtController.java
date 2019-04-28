package hello.controllers;

import hello.dao.ProductForm;
import hello.dao.CommentForm;
import hello.dao.TShirtForm;
import hello.model.Basket;
import hello.model.Product;
import hello.model.TShirt;
import hello.model.Tag;
import hello.service.TShirtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/TShirts")
@CrossOrigin
public class TShirtController {
    @Autowired
    private TShirtService tShirtService;

    @PostMapping("/add")
    public TShirt create(@RequestBody TShirtForm tShirtForm, HttpServletRequest request) {
        return tShirtService.Create(tShirtForm,request);
    }

    @GetMapping("/TShirt/{id}")
    public TShirt findOne(@PathVariable("id") int id) {
        return tShirtService.findOne(id);
    }

    @GetMapping("/all")
    public List<TShirt> findAll() {
        return tShirtService.findAll();
    }

    @GetMapping("/user/tshirts")
    public List<TShirt> getUsersTshirts(HttpServletRequest httpRequest){
        return tShirtService.getUsersTshirts(httpRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTshirt(@PathVariable ( "id" ) int id){
        return tShirtService.delete(id);
    }
}