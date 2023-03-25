package bg.softuni.kidscare.model.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginBindingModel {

    private String username;
    private String password;

    public UserLoginBindingModel() {

    }

    @NotBlank
    @Size(min = 3, max = 30)
    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotBlank
    @Size(min = 5, max = 40)
    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
