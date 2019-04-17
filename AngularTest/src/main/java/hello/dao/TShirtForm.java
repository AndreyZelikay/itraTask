package hello.dao;

import org.w3c.dom.Text;

import javax.validation.constraints.NotNull;

public class TShirtForm {
    @NotNull
    private String url;
    @NotNull
    private String Description;

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
