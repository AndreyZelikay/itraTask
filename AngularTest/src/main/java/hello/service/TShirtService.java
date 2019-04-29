package hello.service;

import hello.Repos.*;
import hello.dao.TShirtForm;
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
    private Token token;

    public TShirt findOne(Integer id){
        return tShirtRepo.getOne(id);
    }

    public TShirt Create(TShirtForm tShirtForm, HttpServletRequest request){
        TShirt tShirt=new TShirt();
        ArrayList<Tag> tags = new ArrayList<Tag>();
        tShirt.setUrl(tShirtForm.getUrl());
        tShirt.setDescription(tShirtForm.getDescription());
        tShirt.setName(tShirtForm.getName());
        tShirt.setTheme(tShirtForm.getTheme());
        tShirt.setJson(tShirtForm.getJson());
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

    public TShirt updateTShirt(TShirtForm tShirtForm,Integer id) {
        TShirt tShirt = tShirtRepo.getOne(id);
        ArrayList<Tag> tags = new ArrayList<Tag>();
        tShirt.setUrl(tShirtForm.getUrl());
        tShirt.setDescription(tShirtForm.getDescription());
        tShirt.setName(tShirtForm.getName());
        tShirt.setTheme(tShirtForm.getTheme());
        tShirt.setJson(tShirtForm.getJson());
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
}