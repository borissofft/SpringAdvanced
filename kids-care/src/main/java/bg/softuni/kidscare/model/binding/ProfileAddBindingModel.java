package bg.softuni.kidscare.model.binding;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class ProfileAddBindingModel {
    private String city;
    private Integer age;
    private Integer phoneNumber;
    private String description;
    private BigDecimal pricePerHour;
    private MultipartFile image;

    public ProfileAddBindingModel() {

    }

    @NotBlank
    @Size(min = 3,max = 25)
    public String getCity() {
        return city;
    }

    public ProfileAddBindingModel setCity(String city) {
        this.city = city;
        return this;
    }

    @NotNull
    @Min(18)
    public Integer getAge() {
        return age;
    }

    public ProfileAddBindingModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    @NotNull
    @Positive
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileAddBindingModel setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @NotBlank
    @Size(min = 10)
    public String getDescription() {
        return description;
    }

    public ProfileAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    @Positive
    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public ProfileAddBindingModel setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public ProfileAddBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
