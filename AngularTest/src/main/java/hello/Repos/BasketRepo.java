package hello.Repos;

import hello.model.ApplicationUser;
import hello.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepo extends JpaRepository<Basket,Integer> {
    Basket findByUser(ApplicationUser user);
}
