package bg.softuni.kidscare.model.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserApproveBindingModel {

    private Long id ;
    private String username;

    public UserApproveBindingModel() {

    }

    public Long getId() {
        return id;
    }

    public UserApproveBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    @NotBlank
    @Size(min = 3, max = 30)
    public String getUsername() {
        return username;
    }

    public UserApproveBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
