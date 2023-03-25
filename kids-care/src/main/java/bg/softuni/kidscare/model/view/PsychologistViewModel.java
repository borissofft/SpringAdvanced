package bg.softuni.kidscare.model.view;

import bg.softuni.kidscare.model.entity.PictureEntity;

public class PsychologistViewModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private PictureEntity picture;

    public Long getId() {
        return id;
    }

    public PsychologistViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PsychologistViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PsychologistViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PsychologistViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PsychologistViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public PsychologistViewModel setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }
}
