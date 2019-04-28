package hello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.List;

@Entity
@Indexed
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TShirt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column( length = 100000 )
    private String url;
    @Column( length = 1000)
    private String json;

    @Field
    private String Description;
    @Field
    private String Name;
    @Field
    private String theme;

    @IndexedEmbedded
    @OneToMany(mappedBy = "tShirt")
    private List<Comments> Comments;

    @IndexedEmbedded
    @OneToMany(mappedBy = "tShirt")
    private List<Tag> Tags;

    @OneToMany(mappedBy = "tShirt")
    private List<Rating> ratings;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ApplicationUser_id")
    private ApplicationUser applicationUser;

    @OneToOne
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<hello.model.Comments> getComments() {
        return Comments;
    }

    public void setComments(List<hello.model.Comments> comments) {
        Comments = comments;
    }

    public List<Tag> getTags() {
        return Tags;
    }

    public void setTags(List<Tag> tags) {
        Tags = tags;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

}
