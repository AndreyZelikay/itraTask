package hello.service;

import hello.Repos.UserRepo;
import hello.dao.RoleForm;
import hello.model.ApplicationUser;
import hello.model.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private UserRepo userRepo;

    public ApplicationUser setRole(RoleForm roleForm){
        ApplicationUser user=userRepo.getOne(roleForm.getId());
        user.setRole(roleForm.getRole());
        return userRepo.save(user);
    }

    public ResponseEntity deleteUser(Integer id){
        userRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public List<ApplicationUser> getAll(){
        return userRepo.findAll();
    }

    public List<TShirt> getUsersTShirts(int id) {
        return userRepo.getOne(id).getTShirts();
    }
}
