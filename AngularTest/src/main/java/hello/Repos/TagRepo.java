package hello.Repos;

import hello.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag,Integer> {
    List<Tag> findAllByBodyAndNumber(String body, Integer number);
    List<Tag> findAllByBody (String body);
}
