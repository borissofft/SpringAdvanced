package bg.softuni.kidscare.model.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity {
    private String fileName;
    private String contentType;
    byte[] content;
    private Set<ProfileEntity> profiles;
    private Set<PsychologistEntity> psychologists;

    public PictureEntity() {

    }


    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public PictureEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    @Column(name = "content_type")
    public String getContentType() {
        return contentType;
    }

    public PictureEntity setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    @Lob()
    @Column(length = Integer.MAX_VALUE, nullable = false)
    public byte[] getContent() {
        return content;
    }

    public PictureEntity setContent(byte[] content) {
        this.content = content;
        return this;
    }

    @OneToMany(mappedBy = "picture", targetEntity = ProfileEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<ProfileEntity> getProfiles() {
        return profiles;
    }

    public PictureEntity setProfiles(Set<ProfileEntity> profiles) {
        this.profiles = profiles;
        return this;
    }

    @OneToMany(mappedBy = "picture", targetEntity = PsychologistEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<PsychologistEntity> getPsychologists() {
        return psychologists;
    }

    public PictureEntity setPsychologists(Set<PsychologistEntity> psychologists) {
        this.psychologists = psychologists;
        return this;
    }
}
