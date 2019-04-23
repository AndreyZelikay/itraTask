package hello.controllers;

import hello.dao.RoleForm;
import hello.dao.UserForm;
import hello.model.ApplicationUser;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity SingUp(@RequestBody UserForm userForm){
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

    @PostMapping("/activate")
    public ResponseEntity Activate(@RequestBody String code){
        return userService.activateUser(code);
    }

    @CrossOrigin
    @GetMapping("/activity")
    public String getActive(HttpServletRequest request){
        return userService.getActivity(request);
    }

    @PostMapping("/activity/set")
    public ApplicationUser setActive(@RequestBody int id){
        return userService.setActivity(id);
    }

    @GetMapping("/activity/reset")
    public ApplicationUser resetActive(HttpServletRequest request){
        return userService.resetActivity(request);
    }
}
