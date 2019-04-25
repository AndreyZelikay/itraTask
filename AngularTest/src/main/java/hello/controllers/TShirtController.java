package hello.controllers;

import hello.dao.CommentForm;
import hello.dao.TShirtForm;
import hello.dao.TagForm;
import hello.model.Comments;
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

    @PostMapping("/comment")
    public Comments setComment(@RequestBody CommentForm commentForm) {
        return tShirtService.setComment(commentForm);
    }

    @PostMapping("/tag")
    public Tag setTag(@RequestBody TagForm tagForm) {
        return tShirtService.setTag(tagForm);
    }

    @GetMapping("/tag/all")
    public List<Tag> getAllTags() {
        return tShirtService.getTags();
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