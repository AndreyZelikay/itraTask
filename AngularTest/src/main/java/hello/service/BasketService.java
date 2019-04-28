package hello.service;

import hello.Repos.BasketRepo;
import hello.Repos.ProductRepo;
import hello.Repos.TShirtRepo;
import hello.Repos.UserRepo;
import hello.dao.ProductForm;
import hello.function.Token;
import hello.model.Basket;
import hello.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class BasketService {
    @Autowired
    private BasketRepo basketRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private TShirtRepo tShirtRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Token token;

    public Basket getBasket(HttpServletRequest httpRequest) {
        Basket basket =  basketRepo.findByUser(userRepo.findByUsername(token.readToken(httpRequest)));
        return basket;
    }

    public ResponseEntity deleteFromBasket(int id) {
        if(productRepo.findById(id)!=null) {
            Product product = productRepo.getOne(id);
            productRepo.delete(product);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    public Product putIntoBasket(ProductForm productForm, HttpServletRequest request) {
        Product product = new Product();
        product.setSize(productForm.getSizes());
        product.settShirt(tShirtRepo.getOne(productForm.gettShirtID()));
        if(basketRepo.findByUser(userRepo.findByUsername(token.readToken(request))) == null){
            Basket basket = new Basket();
            basket.setUser(userRepo.findByUsername(token.readToken(request)));
            basketRepo.save(basket);
            product.setBasket(basket);
        }
        product.setBasket(basketRepo.findByUser(userRepo.findByUsername(token.readToken(request))));
        return productRepo.save(product);
    }
}
