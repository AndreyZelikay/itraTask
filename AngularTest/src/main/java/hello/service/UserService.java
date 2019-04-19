package hello.service;

import hello.Repos.UserRepo;
import hello.dao.UserForm;
import hello.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationUser SignUp(UserForm userForm){
        ApplicationUser user = new ApplicationUser();
        user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        user.setEmail(userForm.getEmail());
        user.setUsername(userForm.getUsername());
        return userRepo.save(user);
    }
}
