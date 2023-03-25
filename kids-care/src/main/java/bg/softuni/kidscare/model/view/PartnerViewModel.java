package bg.softuni.kidscare.model.view;

public class PartnerViewModel {

    private Long id;
    private String name;
    private String description;
    private String partnerUrl;

    public PartnerViewModel() {

    }

    public Long getId() {
        return id;
    }

    public PartnerViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PartnerViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PartnerViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPartnerUrl() {
        return partnerUrl;
    }

    public PartnerViewModel setPartnerUrl(String partnerUrl) {
        this.partnerUrl = partnerUrl;
        return this;
    }
}
