package com.efant.efant.model.entities;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "roles", schema = "efant", catalog = "postgres")
public class Role {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Basic
    @Column(name = "role_name")
    private String roleName;


    @OneToMany(mappedBy = "role")
    private List<User> users;


    // Constructors
    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }


    // Getters and Setters

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }





    // equals and hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) && Objects.equals(roleName, role.roleName) && Objects.equals(users, role.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, users);
    }
}
