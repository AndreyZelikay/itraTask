package hello.controllers;

import hello.dao.CommentForm;
import hello.dao.TShirtForm;
import hello.dao.TagForm;
import hello.model.Comments;
import hello.model.TShirt;
import hello.model.Tag;
import hello.service.TShirtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/TShirts")
@CrossOrigin
public class TShirtController {
    @Autowired
    private TShirtService tShirtService;

    @PostMapping("/add")
    public TShirt create(@RequestBody TShirtForm tShirtForm){
        return tShirtService.Create(tShirtForm);
    }

    @GetMapping("/TShirt/{id}")
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

    @PostMapping("/comment")
    public Comments setComment(@RequestBody CommentForm commentForm){
        return tShirtService.setComment(commentForm);
    }

    @PostMapping("/tag")
    public Tag setTag(@RequestBody TagForm tagForm){
        return tShirtService.setTag(tagForm);
    }

    @GetMapping("/tags")
    public List<Tag> getAllTags(){
        return tShirtService.getTags();
    }
}