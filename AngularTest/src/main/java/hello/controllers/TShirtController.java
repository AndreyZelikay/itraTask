package hello.controllers;

import hello.dao.CommentForm;
import hello.dao.TShirtForm;
import hello.dao.TagForm;
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

    @PostMapping("/comments/add")
    public ResponseEntity setComment(@RequestBody CommentForm commentForm, HttpServletRequest httpRequest) {
        return tShirtService.setComment(commentForm, httpRequest);
    }

    @DeleteMapping("/comments/del/{id}")
    public ResponseEntity deleteComment(@PathVariable ( "id" ) int id, HttpServletRequest httpRequest){
        return tShirtService.deleteComment(id,httpRequest);
    }

    @PostMapping("/feedback/rating/set/{id}")
    public ResponseEntity setRating(@PathVariable ("id") int id, @RequestBody Integer rating){
        return tShirtService.setRating(id,rating);
    }

    @GetMapping("/feedback/rating/get/{id}")
    public Integer getRating(@PathVariable ("id") int id){
        return tShirtService.getRating(id);
    }

    @PostMapping("/feedback/likes/set/{id}")
    public ResponseEntity setLike(@PathVariable ("id") int id, @RequestBody Integer commentId, HttpServletRequest httpRequest ){
        return this.tShirtService.setLike( commentId,httpRequest);
    }
}