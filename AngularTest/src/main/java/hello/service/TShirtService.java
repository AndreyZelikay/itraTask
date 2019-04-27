package hello.service;

import hello.Repos.*;
import hello.dao.CommentForm;
import hello.dao.ProductForm;
import hello.dao.TShirtForm;
import hello.function.ArrayListFind;
import hello.function.Token;
import hello.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class TShirtService {
    @Autowired
    private TShirtRepo tShirtRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private HibernateSearchService searchService;
    @Autowired
    private Token token;
    @Autowired
    private CommentLikeRepo commentLikeRepo;
    @Autowired
    private ArrayListFind arrayListFind;
    @Autowired
    private BasketRepo basketRepo;
    @Autowired
    private ProductRepo productRepo;

    public TShirt findOne(Integer id){
        return tShirtRepo.getOne(id);
    }

    public TShirt Create(TShirtForm tShirtForm, HttpServletRequest request){
        Token token=new Token();
        TShirt tShirt=new TShirt();
        ArrayList<Tag> tags = new ArrayList<Tag>();
        tShirt.setUrl(tShirtForm.getUrl());
        tShirt.setDescription(tShirtForm.getDescription());
        tShirt.setName(tShirtForm.getName());
        tShirt.setApplicationUser(userRepo.findByUsername(token.readToken(request)));
        tShirtRepo.save(tShirt);
        for (String name: tShirtForm.getTags()){
            Tag tag = new Tag();
            tag.settShirt(tShirt);
            tag.setBody(name);
            tagRepo.save(tag);
            tags.add(tag);
        }
        tShirt.setTags(tags);
        return tShirtRepo.save(tShirt);
    }

    public List<TShirt> findAll(){
        return tShirtRepo.findAll();
    }

    public ResponseEntity setComment(CommentForm commentForm, HttpServletRequest httpRequest) {
        Date date = new Date();
        Comments comment = new Comments();
        if(commentRepo.findByUserNameAndTShirt(token.readToken(httpRequest),tShirtRepo.getOne(commentForm.gettShirtId()))==null) {
            comment.setComment(commentForm.getComment());
            comment.setUserName(token.readToken(httpRequest));
            comment.setLastModifiedOn(date);
            comment.settShirt(tShirtRepo.getOne(commentForm.gettShirtId()));
            commentRepo.save(comment);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    public List<Tag> getTags() {
        ArrayList<Tag> result = new ArrayList<>();
        for (Tag tag :tagRepo.findAll()){
            tag.setNumber(tagRepo.findAllByBody(tag.getBody()).size());
            if(!arrayListFind.containsName(result, tag.getBody()))
                result.add(tag);
        }
        return result;
    }

    public List<TShirt> searchTShirt(String search){
        return searchService.SearchTshirt(search);
    }

    public List<Tag> searchTag(String search) {
        return searchService.SearchTag(search);
    }

    public List<TShirt> getUsersTshirts(HttpServletRequest httpRequest) {
        ApplicationUser user=userRepo.findByUsername(token.readToken(httpRequest));
        return user.getTShirts();
    }

    public ResponseEntity delete(int id) {
        TShirt tShirt = tShirtRepo.getOne(id);
        for ( Comments comment : tShirt.getComments()){
            commentRepo.delete(comment);
        }
        for( Tag tag : tShirt.getTags()){
            tagRepo.delete(tag);
        }
        tShirtRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity deleteComment(int id, HttpServletRequest httpRequest) {
        Comments comment = commentRepo.findByUserNameAndTShirt(token.readToken(httpRequest),tShirtRepo.getOne(id));
        if(comment != null) {
            for (CommentLike like: comment.getLikes())
                commentLikeRepo.delete(like);
            commentRepo.delete(comment);
            return  ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    public ResponseEntity setRating(int id, Integer rating) {
        TShirt tShirt = tShirtRepo.getOne(id);
        if(tShirt.getRating() != null) {
            tShirt.setRating(tShirt.getRating() + rating);
            tShirt.setCounterRating(tShirt.getCounterRating() + 1);
        }
        else {
            tShirt.setRating(rating);
            tShirt.setCounterRating(1);
        }
        tShirtRepo.save(tShirt);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public Integer getRating(int id) {
        TShirt tShirt = tShirtRepo.getOne(id);
        if(tShirt.getRating() != null) {
            return tShirt.getRating() / tShirt.getCounterRating();
        }
        return 0;
    }

    public ResponseEntity setLike(Integer commentId, HttpServletRequest httpRequest) {
        Comments comment = commentRepo.getOne(commentId);
        if(commentLikeRepo.findByAuthorAndComments(token.readToken(httpRequest),comment)==null) {
            CommentLike like = new CommentLike();
            like.setAuthor(token.readToken(httpRequest));
            like.setComments(comment);
            commentLikeRepo.save(like);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

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