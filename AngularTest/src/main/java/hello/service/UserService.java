package hello.service;

import hello.Repos.CommentLikeRepo;
import hello.Repos.CommentRepo;
import hello.Repos.UserRepo;
import hello.dao.RoleForm;
import hello.dao.UserForm;
import hello.function.JsonString;
import hello.function.Token;
import hello.function.UserRegistrationSuccess;
import hello.model.ApplicationUser;
import hello.model.CommentLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private  UserRegistrationSuccess registrationSuccess;
    @Autowired
    private Token token;
    @Autowired
    private CommentLikeRepo likeRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private JsonString jsonString;

    public ResponseEntity SignUp(UserForm userForm) {
        JsonString jsonString= new JsonString();
        String str="";
        if (userRepo.findByUsername(userForm.getUsername()) != null) {
            str+="User with this name already exist. ";
        }
        if (userRepo.findByEmail(userForm.getEmail()) != null) {
            str+="User with this email already exist.";
        }
        if(userRepo.findByUsername(userForm.getUsername()) == null && userRepo.findByEmail(userForm.getEmail()) == null){
            registrationSuccess.registrateUser(userForm);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        else {
            try {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonString.ReturnInfo(str));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    public ResponseEntity activateUser(String code) {
        String str="";
        ApplicationUser user=userRepo.findByActivationCode(code);
        if(user==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        else {
            user.setActivationCode(null);
            user.setRole("USER");
            userRepo.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    public Boolean getActivity(HttpServletRequest request) {
        return userRepo.findByUsername(token.readToken(request)).getActive();
    }

    public ApplicationUser setActivity(int id) {
        ApplicationUser user=userRepo.getOne(id);
        user.setActive(false);
        return userRepo.save(user);
    }

    public ApplicationUser resetActivity(HttpServletRequest request) {
        ApplicationUser user = userRepo.findByUsername(token.readToken(request));
        user.setActive(true);
        return userRepo.save(user);
    }

    public String getAchievements(HttpServletRequest request){
        ApplicationUser user = userRepo.findByUsername(token.readToken(request));
        String achievements = "";
        if(user.getTShirts().size() >= 5){
            achievements += "5 designs \n";
        }
        if(user.getActivationCode() == null){
            achievements +="successful registration \n";
        }
        if(likeRepo.findAllByAuthor(token.readToken(request)).size() >= 5){
            achievements += "5 likes \n";
        }
        if(commentRepo.findAllByUserName(token.readToken(request)).size() >= 5){
            achievements +="5 comments \n";
        }
        try {
            achievements = jsonString.ReturnInfo(achievements);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return achievements;
    }

    public ResponseEntity sendEmail(String email) {
        return registrationSuccess.sendEmail(email);
    }
}
