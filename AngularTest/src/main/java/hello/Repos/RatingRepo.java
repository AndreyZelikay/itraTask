package hello.Repos;

import hello.model.Rating;
import hello.model.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepo extends JpaRepository<Rating,Integer> {
    Rating findByAuthorAndTShirt(String author, TShirt tShirt);
}
