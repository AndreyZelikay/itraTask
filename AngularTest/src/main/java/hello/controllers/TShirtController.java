package hello.controllers;

import hello.dao.TShirtForm;
import hello.model.TShirt;
import hello.service.TShirtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/TShirts")
@CrossOrigin
public class TShirtController {
    @Autowired
    private TShirtService tShirtService;

    @PostMapping("/add")
    public TShirt create(@RequestBody TShirtForm tShirtForm){
        return tShirtService.Create(tShirtForm);
    }

    @GetMapping("/TShirt/{id}")
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