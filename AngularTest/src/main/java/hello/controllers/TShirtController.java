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

    @GetMapping("/last")
    public TShirt getLast() {
        return tShirtService.findOne(tShirtService.findAll().size());
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

    @PostMapping("/search/tshirt")
    public List<TShirt> SearchTshirt(@RequestBody String search) {
        return tShirtService.searchTShirt("cab");
    }

    @PostMapping("/search/tag")
    public List<Tag> SearchTag(@RequestBody String search) {
        return tShirtService.searchTag("s*");
    }
}