package hello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Indexed
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date lastModifiedOn;

    @Column( length = 100000 )
    @Field
    private String Comment;
    private String userName;

    @OneToMany(mappedBy = "comments")
    private List<CommentLike> Likes;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tShirt_id")
    @ContainedIn
    private TShirt tShirt;

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public void settShirt(TShirt tShirt) {
        this.tShirt = tShirt;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public TShirt gettShirt() {
        return tShirt;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public List<CommentLike> getLikes() {
        return Likes;
    }

    public void setLikes(List<CommentLike> likes) {
        Likes = likes;
    }
}
