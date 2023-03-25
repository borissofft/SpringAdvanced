package bg.softuni.kidscare.model.service;

import org.springframework.web.multipart.MultipartFile;

public class PsychologistServiceModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private MultipartFile image;

    public PsychologistServiceModel() {

    }

    public Long getId() {
        return id;
    }

    public PsychologistServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PsychologistServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PsychologistServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PsychologistServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PsychologistServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public PsychologistServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
