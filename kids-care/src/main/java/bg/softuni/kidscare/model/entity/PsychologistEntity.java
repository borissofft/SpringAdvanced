package bg.softuni.kidscare.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "psychologists")
public class PsychologistEntity extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private PictureEntity picture;

    public PsychologistEntity() {

    }


    @Column(name = "first_name", length = 20, nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public PsychologistEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(name = "last_name", length = 20, nullable = false)
    public String getLastName() {
        return lastName;
    }

    public PsychologistEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(name = "email", length = 30, nullable = false)
    public String getEmail() {
        return email;
    }

    public PsychologistEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public PsychologistEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public PictureEntity getPicture() {
        return picture;
    }

    public PsychologistEntity setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }
}
