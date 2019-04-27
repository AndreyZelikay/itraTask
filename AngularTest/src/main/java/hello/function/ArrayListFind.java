package hello.function;

import hello.model.Tag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ArrayListFind {
    public  boolean containsName(ArrayList<Tag> transaction, String name) {
        for (Tag tag : transaction) {
            if (tag.getBody().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
