package hello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.Set;

@Entity
@Indexed
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TShirt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column( length = 100000 )
    private String url;
    @Field
    private String Description;
    @Field
    private String Name;

    private Integer Rating;

    @IndexedEmbedded
    @OneToMany(mappedBy = "tShirt")
    private Set<Comments> Comments;

    @IndexedEmbedded
    @OneToMany(mappedBy = "tShirt")
    private Set<Tag> Tags;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ApplicationUser_id")
    private ApplicationUser applicationUser;

    public Set<hello.model.Comments> getComments() {
        return Comments;
    }

    public void setComments(Set<hello.model.Comments> comments) {
        Comments = comments;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public Set<Tag> getTags() {
        return Tags;
    }

    public void setTags(Set<Tag> tags) {
        Tags = tags;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public TShirt() {}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return Description;
    }

    public Integer getId() {
        return id;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getRating() {
        return Rating;
    }

    public void setRating(Integer rating) {
        Rating = rating;
    }
}
