package bg.softuni.kidscare.model.service;

public class PartnerServiceModel {

    private Long id;
    private String name;
    private String description;
    private String partnerUrl;

    public PartnerServiceModel() {

    }

    public Long getId() {
        return id;
    }

    public PartnerServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PartnerServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PartnerServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPartnerUrl() {
        return partnerUrl;
    }

    public PartnerServiceModel setPartnerUrl(String partnerUrl) {
        this.partnerUrl = partnerUrl;
        return this;
    }
}
