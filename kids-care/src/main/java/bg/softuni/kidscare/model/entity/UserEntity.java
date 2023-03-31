package bg.softuni.kidscare.model.entity;

import bg.softuni.kidscare.model.enums.UserRequestTypeEnum;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String username;
    private String email;
    private String password;
    private UserRequestTypeEnum requestedType;
    private boolean isApproved;
    private Set<UserRoleEntity> roles;

    public UserEntity() {

    }

    @Column(name = "username", length = 30, nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(name = "email", length = 30, nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "requested_type", nullable = false)
    public UserRequestTypeEnum getRequestedType() {
        return requestedType;
    }

    public UserEntity setRequestedType(UserRequestTypeEnum requestedType) {
        this.requestedType = requestedType;
        return this;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public UserEntity setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }

//    @ManyToMany()
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
