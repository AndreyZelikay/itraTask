package hello.service;

import hello.Repos.*;
import hello.dao.CommentForm;
import hello.dao.TShirtForm;
import hello.dao.TagForm;
import hello.function.Token;
import hello.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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

    public TShirt findOne(Integer id){
        return tShirtRepo.getOne(id);
    }

    public TShirt Create(TShirtForm tShirtForm, HttpServletRequest request){
        Token token=new Token();
        TShirt tShirt=new TShirt();
        tShirt.setUrl(tShirtForm.getUrl());
        tShirt.setDescription(tShirtForm.getDescription());
        tShirt.setName(tShirtForm.getName());
        tShirt.setApplicationUser(userRepo.findByUsername(token.readToken(request)));
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

    public Tag setTag(TagForm tagForm) {
        Tag tag=new Tag();
        if(tagRepo.findByBody(tagForm.getBody())!=null) {
            tag=tagRepo.findByBody(tagForm.getBody());
            tag.setNumber(tag.getNumber()+1);
        }
        else{
        tag.setBody(tagForm.getBody());
        tag.setNumber(1);
        }
        tag.settShirt(tShirtRepo.getOne(tagForm.gettShirtId()));
        return tagRepo.save(tag);
    }

    public List<Tag> getTags() {
        return tagRepo.findAll();
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
        tShirtRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity deleteComment(int id, HttpServletRequest httpRequest) {
        Comments comment = commentRepo.findByUserNameAndTShirt(token.readToken(httpRequest),tShirtRepo.getOne(id));
        if(comment != null) {
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
}