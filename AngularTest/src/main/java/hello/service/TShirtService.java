package hello.service;

import hello.Repos.CommentRepo;
import hello.Repos.TShirtRepo;
import hello.Repos.TagRepo;
import hello.Repos.UserRepo;
import hello.dao.CommentForm;
import hello.dao.TShirtForm;
import hello.dao.TagForm;
import hello.function.Token;
import hello.model.ApplicationUser;
import hello.model.Comments;
import hello.model.TShirt;
import hello.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    public TShirt findOne(Integer id){
        return tShirtRepo.getOne(id);
    }

    public TShirt Create(TShirtForm tShirtForm, HttpServletRequest request){
        Token token=new Token();
        TShirt tShirt=new TShirt();
        tShirt.setUrl(tShirtForm.getUrl());
        tShirt.setDescription(tShirtForm.getDescription());
        tShirt.setName(tShirtForm.getName());
        tShirt.setRating(tShirtForm.getRating());
        tShirt.setApplicationUser(userRepo.findByUsername(token.readToken(request)));
        return tShirtRepo.save(tShirt);
    }

    public List<TShirt> findAll(){
        return tShirtRepo.findAll();
    }

    public Comments setComment(CommentForm commentForm) {
        Comments comment = new Comments();
        comment.setComment(commentForm.getComment());
        comment.setUserName(comment.getUserName());
        comment.setLastModifiedOn(commentForm.getLastModifiedOn());
        comment.settShirt(tShirtRepo.getOne(commentForm.gettShirtId()));
        return commentRepo.save(comment);
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
        tShirtRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
