package hello.service;

import hello.Repos.TShirtRepo;
import hello.Repos.TagRepo;
import hello.Repos.UserRepo;
import hello.dao.RoleForm;
import hello.dao.TShirtForm;
import hello.model.ApplicationUser;
import hello.model.TShirt;
import hello.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TShirtRepo tShirtRepo;
    @Autowired
    private TagRepo tagRepo;

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

    public TShirt updateTshirtByAdmin(int id, TShirtForm tShirtForm) {
        TShirt tShirt = tShirtRepo.getOne(id);
        ArrayList<Tag> tags = new ArrayList<Tag>();
        tShirt.setUrl(tShirtForm.getUrl());
        tShirt.setDescription(tShirtForm.getDescription());
        tShirt.setName(tShirtForm.getName());
        tShirt.setTheme(tShirtForm.getTheme());
        tShirt.setJson(tShirtForm.getJson());
        tShirt.setApplicationUser(userRepo.getOne(id));
        tShirtRepo.save(tShirt);
        for (String name: tShirtForm.getTags()){
            Tag tag = new Tag();
            tag.settShirt(tShirt);
            tag.setBody(name);
            tagRepo.save(tag);
            tags.add(tag);
        }
        tShirt.setTags(tags);
        return tShirtRepo.save(tShirt);
    }
}
