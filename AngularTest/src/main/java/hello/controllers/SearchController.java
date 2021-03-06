package hello.controllers;

import hello.model.TShirt;
import hello.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping("/tshirt")
    public List<TShirt> SearchTshirt(@RequestBody String search) {
        return searchService.searchTShirt(search+"*");
    }

    @PostMapping("/tag")
    public List<TShirt> SearchByTag(@RequestBody String search) {
        return searchService.searchByTag(search+"*");
    }
}