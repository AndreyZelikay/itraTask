package hello.Repos;

import hello.model.Comments;
import hello.model.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comments,Integer> {
    Comments findByUserNameAndTShirt(String username, TShirt tShirt);
    List<Comments> findAllByUserName(String username);
}
