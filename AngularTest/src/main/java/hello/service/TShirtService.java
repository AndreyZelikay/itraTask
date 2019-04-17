package hello.service;

import hello.Repos.TShirtRepo;
import hello.dao.TShirtForm;
import hello.model.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TShirtService {
    @Autowired
    private TShirtRepo tShirtRepo;

    public TShirt findOne(Integer id){
        return tShirtRepo.getOne(id);
    }

    public TShirt Create(TShirtForm tShirtForm){
        TShirt tShirt=new TShirt();
        tShirt.setUrl(tShirtForm.getUrl());
        tShirt.setDescription(tShirtForm.getDescription());
        return tShirtRepo.save(tShirt);
    }

    public List<TShirt> findAll(){
        return tShirtRepo.findAll();
    }

}
