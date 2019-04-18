package hello.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date lastModifiedOn;

    @Column( length = 100000 )
    private String Comment;
    private String UserName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tShirt_id",nullable=false,unique=true)
    private TShirt tShirt;

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public TShirt gettShirt() {
        return tShirt;
    }

    public Integer gettShirtId(){
        return tShirt.getId();
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
