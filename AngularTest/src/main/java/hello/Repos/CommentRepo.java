package hello.Repos;

import hello.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comments,Integer> {
}
