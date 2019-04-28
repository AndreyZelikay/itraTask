package hello.controllers;

import hello.dao.RoleForm;
import hello.dao.TShirtForm;
import hello.model.ApplicationUser;
import hello.model.TShirt;
import hello.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/all")
    public List<ApplicationUser> GetAll(){
        return adminService.getAll();
    }
    @PostMapping("/role")
    public ApplicationUser SetRole(@RequestBody RoleForm roleForm){
        return adminService.setRole(roleForm);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity DeleteUser(@PathVariable( "id" ) int id){
        return adminService.deleteUser(id);
    }
    @GetMapping("/user/{id}/tshirts")
    public List<TShirt> getUsersTShirts(@PathVariable( "id" ) int id){
        return this.adminService.getUsersTShirts(id);
    }
    @PostMapping("/user/{id}/tshirts")
    public TShirt updateTshirtByAdmin(@PathVariable( "id" ) int id, TShirtForm tShirtForm){
        return this.adminService.updateTshirtByAdmin(id,tShirtForm);
    }
}
