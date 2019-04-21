package hello.service;

import hello.Repos.UserRepo;
import hello.dao.RoleForm;
import hello.dao.UserForm;
import hello.function.JsonString;
import hello.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String SignUp(UserForm userForm){
        ApplicationUser user = new ApplicationUser();
        String str;
        if(userRepo.findByUsername(userForm.getUsername())!=null) {
            str = "Error!";
        }
        else {
            str = "Success!";
            user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
            user.setEmail(userForm.getEmail());
            user.setUsername(userForm.getUsername());
            userRepo.save(user);

        }
        JsonString jsonString=new JsonString();
        try {
            str=jsonString.ReturnInfo(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
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
}
