package hello.dao;


import hello.model.Comments;

import javax.validation.constraints.NotNull;
import java.util.List;

public class TShirtForm {
    @NotNull
    private String url;
    @NotNull
    private String Description;
    @NotNull
    private String Name;
    @NotNull
    private String Tags;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
