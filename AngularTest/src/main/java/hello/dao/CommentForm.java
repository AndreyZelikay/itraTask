package hello.dao;

import java.util.Date;

public class CommentForm {
    private String comment;
    private String username;
    private Date lastModifiedOn;
    private Integer tShirtId;

    public Integer gettShirtId() {
        return tShirtId;
    }

    public void settShirtId(Integer tShirtId) {
        this.tShirtId = tShirtId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }
}
