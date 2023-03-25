package bg.softuni.kidscare.model.view;

import bg.softuni.kidscare.model.entity.PictureEntity;
import bg.softuni.kidscare.model.entity.UserEntity;

import java.math.BigDecimal;

public class ProfileViewModel {

    private Long id;
    private String city;
    private Integer age;
    private Integer phoneNumber;
    private String description;
    private BigDecimal pricePerHour;
    private PictureEntity picture;
    private UserEntity user;

    public ProfileViewModel() {

    }

    public Long getId() {
        return id;
    }

    public ProfileViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ProfileViewModel setCity(String city) {
        this.city = city;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public ProfileViewModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileViewModel setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProfileViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public ProfileViewModel setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
        return this;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public ProfileViewModel setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public ProfileViewModel setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
