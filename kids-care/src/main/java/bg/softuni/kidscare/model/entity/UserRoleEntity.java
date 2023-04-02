package bg.softuni.kidscare.model.entity;

import bg.softuni.kidscare.model.enums.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {
    private UserRoleEnum role;

    public UserRoleEntity() {

    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserRoleEntity{");
        sb.append("role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
