package hello.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="ApplicationUser")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String Password;
    private String Email;

    @ElementCollection(targetClass = Roles.class,fetch=FetchType.LAZY)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name="ApplicationUser_id"))
    @Enumerated(EnumType.STRING)
    private List<Roles> role;

    public List<Roles> getRole() {
        return role;
    }

    public void setRole(List<Roles> role) {
        this.role = role;
    }

    public ApplicationUser(){}

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
