package hello.dao;

import hello.model.ApplicationUser;

public class ProductForm {
    private Integer tShirtID;
    private String sizes;
    private ApplicationUser user;

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public Integer gettShirtID() {
        return tShirtID;
    }

    public void settShirtID(Integer tShirtID) {
        this.tShirtID = tShirtID;
    }
}
