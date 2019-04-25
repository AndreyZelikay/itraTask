package hello.function;

import hello.Repos.UserRepo;
import hello.dao.UserForm;
import hello.model.ApplicationUser;
import hello.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserRegistrationSuccess {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private UserRepo userRepo;

    public void registrateUser(UserForm userForm){
        ApplicationUser user = new ApplicationUser();
        user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        user.setEmail(userForm.getEmail());
        user.setUsername(userForm.getUsername());
        user.setActivationCode(UUID.randomUUID().toString());
        if (!user.getEmail().isEmpty()) {
            String message = String.format("Hello %s \n" +
                            " Welcome to T-Shirt Shop! to complete your registration follow next link: http://localhost:4200/activate/%s",
                    user.getUsername(), user.getActivationCode());
            emailSender.send(user.getEmail(), "Activation code", message);
        }
        user.setRole("ANONYMOUS");
        user.setActive(true);
        userRepo.save(user);
    }
}
