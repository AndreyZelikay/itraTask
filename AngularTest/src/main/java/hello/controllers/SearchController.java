package hello.controllers;

import hello.model.TShirt;
import hello.model.Tag;
import hello.service.TShirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SearchController {
    @Autowired
    private TShirtService tShirtService;

    @PostMapping("/tshirt")
    public List<TShirt> SearchTshirt(@RequestBody String search) {
        return tShirtService.searchTShirt(search+"*");
    }

    @PostMapping("/tag")
    public List<Tag> SearchTag(@RequestBody String search) {
        return tShirtService.searchTag(search+"*");
    }
}