package bg.softuni.kidscare.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "profiles")
public class ProfileEntity extends BaseEntity {
    private String city;
    private Integer age;
    private Integer phoneNumber;
    private String description;
    private BigDecimal pricePerHour;
    private PictureEntity picture;
    private UserEntity user;

    public ProfileEntity() {

    }

    @Column(name = "city", length = 25, nullable = false)
    public String getCity() {
        return city;
    }

    public ProfileEntity setCity(String city) {
        this.city = city;
        return this;
    }

    @Column(name = "age", nullable = false)
    public Integer getAge() {
        return age;
    }

    public ProfileEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Column(name = "phone_number", nullable = false)
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileEntity setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public ProfileEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "price_per_hour",nullable = false)
    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public ProfileEntity setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
        return this;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public PictureEntity getPicture() {
        return picture;
    }

    public ProfileEntity setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public UserEntity getUser() {
        return user;
    }

    public ProfileEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
