package hello.Repos;

import hello.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag,Integer> {
    Tag findByBody(String body);
}
