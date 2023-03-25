package bg.softuni.kidscare.model.service;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class ProfileServiceModel {

    private  Long id;
    private String city;
    private Integer age;
    private Integer phoneNumber;
    private String description;
    private BigDecimal pricePerHour;
    private MultipartFile image;

    public ProfileServiceModel() {

    }

    public Long getId() {
        return id;
    }

    public ProfileServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ProfileServiceModel setCity(String city) {
        this.city = city;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public ProfileServiceModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileServiceModel setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProfileServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public ProfileServiceModel setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public ProfileServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
