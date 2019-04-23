package hello.service;

import hello.Repos.UserRepo;
import hello.dao.RoleForm;
import hello.dao.UserForm;
import hello.function.JsonString;
import hello.function.Token;
import hello.function.UserRegistrationSuccess;
import hello.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

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
            UserRegistrationSuccess registrationSuccess=new UserRegistrationSuccess();
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

    public List<ApplicationUser> getAll(){
        return userRepo.findAll();
    }

    public ApplicationUser setRole(RoleForm roleForm){
        ApplicationUser user=userRepo.getOne(roleForm.getId());
        user.setRole(roleForm.getRole());
        return userRepo.save(user);
    }

    public void deleteUser(Integer id){
        userRepo.deleteById(id);
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

    public String getActivity(HttpServletRequest request) {
        Token token=new Token();
        String Result="";
        JsonString jsonString=new JsonString();
        try {
            Result=jsonString.ReturnActivity(userRepo.findByUsername(token.readToken(request)).getActive().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result;
    }

    public ApplicationUser setActivity(int id) {
        ApplicationUser user=userRepo.getOne(id);
        user.setActive(false);
        return userRepo.save(user);
    }

    public ApplicationUser resetActivity(HttpServletRequest request) {
        Token token=new Token();
        ApplicationUser user = userRepo.findByUsername(token.readToken(request));
        user.setActive(true);
        return userRepo.save(user);
    }
}
