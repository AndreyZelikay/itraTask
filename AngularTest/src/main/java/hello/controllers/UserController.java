package hello.controllers;

import hello.dao.UserForm;
import hello.model.User;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-in")//Войти
    public User SingIn(@RequestBody UserForm userForm){
        return userService.SignIn(userForm);
    }

    @PostMapping("/sign-up")//Зарегистрироваться
    public  User SingUp(@RequestBody UserForm userForm){
        return userService.SignUp(userForm);
    }
}
