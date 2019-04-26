package hello.Repos;

import hello.model.Comments;
import hello.model.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepo extends JpaRepository<CommentLike,Integer> {
    CommentLike findByAuthorAndComments(String author, Comments comment);
}
