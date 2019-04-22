package hello.dao;

import hello.model.Tag;

import javax.validation.constraints.NotNull;

public class TShirtForm {
    @NotNull
    private String url;
    @NotNull
    private String Description;
    @NotNull
    private String Name;
    @NotNull
    private Integer AuthorId;

    public Integer getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(Integer authorId) {
        AuthorId = authorId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
