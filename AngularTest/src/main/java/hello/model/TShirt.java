package hello.model;

import org.w3c.dom.Text;

import javax.persistence.*;

@Entity
public class TShirt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column( length = 100000 )
    private String url;
    private String Description;


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
}
