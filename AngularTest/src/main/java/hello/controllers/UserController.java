package hello.controllers;

import hello.dao.UserForm;
import hello.model.ApplicationUser;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/activate")
    public ResponseEntity Activate(@RequestBody String code){
        return userService.activateUser(code);
    }

    @GetMapping("/activity")
    public Boolean getActive(HttpServletRequest request){
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

    @GetMapping("/achievements")
    public String getAchievements(HttpServletRequest request){
        return userService.getAchievements(request);
    }
}
