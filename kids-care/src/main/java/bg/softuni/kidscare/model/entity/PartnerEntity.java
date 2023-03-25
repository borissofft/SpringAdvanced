package bg.softuni.kidscare.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "partners")
public class PartnerEntity extends BaseEntity {
    private String name;
    private String description;
    private String partnerUrl;

    public PartnerEntity() {

    }

    @Column(name = "name", length = 30, nullable = false)
    public String getName() {
        return name;
    }

    public PartnerEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public PartnerEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "partner_url", length = 40, nullable = false)
    public String getPartnerUrl() {
        return partnerUrl;
    }

    public PartnerEntity setPartnerUrl(String partnerUrl) {
        this.partnerUrl = partnerUrl;
        return this;
    }
}
