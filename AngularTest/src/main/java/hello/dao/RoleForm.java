package hello.dao;

import javax.validation.constraints.NotNull;

public class RoleForm {
    @NotNull
    private String Role;
    @NotNull
    private Integer id;

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
