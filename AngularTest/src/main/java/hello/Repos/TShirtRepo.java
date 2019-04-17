package hello.Repos;

import hello.model.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TShirtRepo extends JpaRepository<TShirt,Integer> {
}
