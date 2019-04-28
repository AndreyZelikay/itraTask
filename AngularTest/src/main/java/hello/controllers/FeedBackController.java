package hello.controllers;

import hello.dao.CommentForm;
import hello.model.Tag;
import hello.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/feedback")
@CrossOrigin
public class FeedBackController {
    @Autowired
    private FeedBackService feedBackService;

    @PostMapping("/comments/add")
    public ResponseEntity setComment(@RequestBody CommentForm commentForm, HttpServletRequest httpRequest) {
        return feedBackService.setComment(commentForm, httpRequest);
    }

    @DeleteMapping("/comments/del/{id}")
    public ResponseEntity deleteComment(@PathVariable( "id" ) int id, HttpServletRequest httpRequest){
        return feedBackService.deleteComment(id,httpRequest);
    }

    @PostMapping("/feedback/rating/set/{id}")
    public ResponseEntity setRating(@PathVariable ("id") int id, @RequestBody Integer rating,HttpServletRequest request){
        return feedBackService.setRating(id,rating,request);
    }
    @GetMapping("/tag/all")
    public List<Tag> getAllTags() {
        return feedBackService.getTags();
    }

    @GetMapping("/feedback/rating/get/{id}")
    public Integer getRating(@PathVariable ("id") int id){
        return feedBackService.getRating(id);
    }

    @PostMapping("/feedback/likes/set")
    public ResponseEntity setLike(@RequestBody Integer commentId, HttpServletRequest httpRequest ){
        return this.feedBackService.setLike( commentId,httpRequest);
    }
}
