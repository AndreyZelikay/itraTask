package hello.controllers;

import hello.Repos.UserRepo;
import hello.dao.RoleForm;
import hello.dao.UserForm;
import hello.model.ApplicationUser;
import hello.model.TShirt;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/sign-up")
    public String SingUp(@RequestBody UserForm userForm){
        return userService.SignUp(userForm);
    }

    @GetMapping("/admin/all")
    public List<ApplicationUser> GetAll(){
        return userService.getAll();
    }
    @PostMapping("/admin/role")
    public ApplicationUser SetRole(@RequestBody RoleForm roleForm){
        return userService.setRole(roleForm);
    }

    @DeleteMapping("/admin/{id}")
    public String DeleteUser(@PathVariable ( "id" ) int id){
        userService.deleteUser(id);
        return "User Deleted";
    }
    @PostMapping("/activate/{code}")
    public String Activate(@PathVariable String code){
        return userService.activateUser(code);
    }
    @GetMapping("/activity/{id}")
    public String getActive(@PathVariable ( "id" ) int id){
        return userService.getActivity(id);
    }
    @PostMapping("/activity/set")
    public ApplicationUser setActive(@RequestBody int id){
        return userService.serActivity(id);
    }
}
