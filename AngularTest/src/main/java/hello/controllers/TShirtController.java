package hello.controllers;

import hello.dao.TShirtForm;
import hello.model.TShirt;
import hello.service.TShirtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/TShirts")
@CrossOrigin
public class TShirtController {
    @Autowired
    private TShirtService tShirtService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TShirt create(@RequestBody TShirtForm tShirtForm){
        return tShirtService.Create(tShirtForm);
    }

    @GetMapping("/{id}")
    public TShirt findOne(@PathVariable ( "id" ) int id){
        return tShirtService.findOne(id);
    }

    @GetMapping("/all")
    public List<TShirt> findAll(){
        return tShirtService.findAll();
    }
    @GetMapping("/last")
    public TShirt getLast(){
        return tShirtService.findOne(tShirtService.findAll().size());
    }
}