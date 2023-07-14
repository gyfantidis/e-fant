package com.efant.efant.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles", schema = "efant", catalog = "postgres")
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id")
    private long roleId;
    @Basic
    @Column(name = "role_name")
    private String roleName;

    @JsonManagedReference
    @OneToMany(mappedBy="role")
    private List<User> users;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role that = (Role) o;

        if (roleId != that.roleId) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }
}
