package bg.softuni.kidscare.model.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class PsychologistAddBindingModel {
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private MultipartFile image;

    public PsychologistAddBindingModel() {

    }


    @NotBlank
    @Size(min = 3, max = 20)
    public String getFirstName() {
        return firstName;
    }

    public PsychologistAddBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotBlank
    @Size(min = 5, max = 20)
    public String getLastName() {
        return lastName;
    }

    public PsychologistAddBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @NotBlank
    @Email
    @Size(min = 8, max = 30)
    public String getEmail() {
        return email;
    }

    public PsychologistAddBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotBlank
    @Size(min = 10)
    public String getDescription() {
        return description;
    }

    public PsychologistAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public PsychologistAddBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
