package hello.service;

import hello.Repos.CommentLikeRepo;
import hello.Repos.CommentRepo;
import hello.Repos.TShirtRepo;
import hello.Repos.TagRepo;
import hello.dao.CommentForm;
import hello.function.ArrayListFind;
import hello.function.Token;
import hello.model.CommentLike;
import hello.model.Comments;
import hello.model.TShirt;
import hello.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FeedBackService {
    @Autowired
    private CommentLikeRepo commentLikeRepo;
    @Autowired
    private ArrayListFind arrayListFind;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private TShirtRepo tShirtRepo;
    @Autowired
    private Token token;

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

}
