package hello.controllers;

import hello.dao.ProductForm;
import hello.model.Basket;
import hello.model.Product;
import hello.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/basket")
@CrossOrigin
public class BasketController {
    @Autowired
    private BasketService basketService;
    @GetMapping("/get")
    public Basket getBasket(HttpServletRequest httpRequest){
        return this.basketService.getBasket(httpRequest);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFromBasket(@PathVariable ("id") int id){
        return this.basketService.deleteFromBasket(id);
    }
    @PostMapping("/put")
    public Product putIntoBasket(@RequestBody ProductForm product, HttpServletRequest request){
        return this.basketService.putIntoBasket(product,request);
    }
}
