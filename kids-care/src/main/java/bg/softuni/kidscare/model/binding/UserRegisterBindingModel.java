package bg.softuni.kidscare.model.binding;

import bg.softuni.kidscare.model.enums.UserRequestTypeEnum;
import bg.softuni.kidscare.vallidation.UniqueUserEmail;
import bg.softuni.kidscare.vallidation.UniqueUserName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterBindingModel {
    private String username;
    private String email;
    private String password;
    private UserRequestTypeEnum requestedType;

    public UserRegisterBindingModel() {

    }

    @NotBlank
    @UniqueUserName
    @Size(min = 3, max = 30)
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotBlank
    @Email
    @UniqueUserEmail
    @Size(min = 8, max = 30)
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotBlank
    @Size(min = 5, max = 40)
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotNull
    public UserRequestTypeEnum getRequestedType() {
        return requestedType;
    }

    public UserRegisterBindingModel setRequestedType(UserRequestTypeEnum requestedType) {
        this.requestedType = requestedType;
        return this;
    }

}
