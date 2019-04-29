package hello.service;

import hello.model.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    private HibernateSearchService searchService;

    public List<TShirt> searchTShirt(String search){
        return searchService.SearchTshirt(search);
    }
    public List<TShirt> searchByTag(String search) {
        return searchService.SearchByTag(search);
    }
}
