package hello.dao;

public class TShirtForm {
    private String url;
    private String Description;
    private String Name;
    private Integer AuthorId;
    private Integer Rating;

    public Integer getRating() {
        return Rating;
    }

    public void setRating(Integer rating) {
        Rating = rating;
    }

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
