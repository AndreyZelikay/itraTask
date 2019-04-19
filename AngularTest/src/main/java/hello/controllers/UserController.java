package hello.controllers;

import hello.dao.UserForm;
import hello.model.ApplicationUser;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    private UserDetailsService userDetailsService;

    @PostMapping("/sign-up")
    public ApplicationUser SingUp(@RequestBody UserForm userForm){
        return userService.SignUp(userForm);
    }
}
