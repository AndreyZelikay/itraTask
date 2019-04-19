package hello.Repos;

import hello.model.ApplicationUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<ApplicationUser,Integer> {
    ApplicationUser findByUsername(String username);
}
