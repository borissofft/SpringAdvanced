package bg.softuni.kidscare.model.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PartnerAddBindingModel {
    private String name;
    private String description;
    private String partnerUrl;

    public PartnerAddBindingModel() {

    }

    @NotBlank
    @Size(min = 3, max = 30)
    public String getName() {
        return name;
    }

    public PartnerAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotBlank
    @Size(min = 10)
    public String getDescription() {
        return description;
    }

    public PartnerAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotBlank
    @Size(min = 9, max = 40)
    public String getPartnerUrl() {
        return partnerUrl;
    }

    public PartnerAddBindingModel setPartnerUrl(String partnerUrl) {
        this.partnerUrl = partnerUrl;
        return this;
    }
}
