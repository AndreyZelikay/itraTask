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
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailSender emailSender;

    public String SignUp(UserForm userForm){
        ApplicationUser user = new ApplicationUser();
        String Status,Code,Result="";
        if(userRepo.findByUsername(userForm.getUsername())!=null || userRepo.findByEmail(userForm.getEmail())!=null) {
            Status = "Error!";
            Code="null";
        }
        else {
            Status = "Success!";
            user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
            user.setEmail(userForm.getEmail());
            user.setUsername(userForm.getUsername());
            user.setActivationCode(UUID.randomUUID().toString());
            if(!user.getEmail().isEmpty()){
                String message=String.format("Hello %s \n" +
                        " Welcome to T-Shirt Shop! to complete your registration follow next link: http://localhost:4200/activate/%s",
                        user.getUsername(),user.getActivationCode());
               emailSender.send(user.getEmail(),"Activation code",message);
               Code=user.getActivationCode();
            }
            else{
            Code="null";
            }
            user.setRole("ANONYMOUS");
            user.setActive(true);
            userRepo.save(user);
        }
        JsonString jsonString=new JsonString();
        try {
            Result=jsonString.RetrunSignUpInfo(Status,Code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result;
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

    public String activateUser(String code) {
        String str="";
        ApplicationUser user=userRepo.findByActivationCode(code);
        if(user==null){
            str="Error!";
        }
        else {
            user.setActivationCode(null);
            userRepo.save(user);
            str = "Success!";
        }
        JsonString jsonString=new JsonString();
        try {
            str=jsonString.ReturnInfo(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getActivity(Integer id) {
        String Result="";
        JsonString jsonString=new JsonString();
        try {
            Result=jsonString.ReturnActivity(userRepo.getOne(id).getActive().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result;
    }

    public ApplicationUser serActivity(int id) {
        ApplicationUser user=userRepo.getOne(id);
        user.setActive(false);
        return userRepo.save(user);
    }
}
