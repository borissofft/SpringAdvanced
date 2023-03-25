package bg.softuni.kidscare.model.service;

import bg.softuni.kidscare.model.enums.UserRequestTypeEnum;

public class UserRegisterServiceModel {
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRequestTypeEnum requestedType;

    public UserRegisterServiceModel() {

    }

    public Long getId() {
        return id;
    }

    public UserRegisterServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserRequestTypeEnum getRequestedType() {
        return requestedType;
    }

    public UserRegisterServiceModel setRequestedType(UserRequestTypeEnum requestedType) {
        this.requestedType = requestedType;
        return this;
    }
}
