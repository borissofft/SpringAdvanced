package bg.softuni.kidscare.web;

import bg.softuni.kidscare.model.binding.PartnerAddBindingModel;
import bg.softuni.kidscare.model.service.PartnerServiceModel;
import bg.softuni.kidscare.model.view.PartnerViewModel;
import bg.softuni.kidscare.service.PartnerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/partners")
public class PartnerController {
    private final PartnerService partnerService;
    private final ModelMapper modelMapper;

    @Autowired
    public PartnerController(PartnerService partnerService, ModelMapper modelMapper) {
        this.partnerService = partnerService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String add() {
        return "partner-add";
    }

    @GetMapping("/all")
    public String allPartners(Model model) {
        List<PartnerViewModel> partnerViewModels = this.partnerService.findAllPartners();
        model.addAttribute("partners", partnerViewModels);
        return "partners";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid PartnerAddBindingModel partnerAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("partnerAddBindingModel", partnerAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.partnerAddBindingModel",
                            bindingResult);

            return "redirect:/partners/add";
        }

        this.partnerService
                .addPartner(this.modelMapper.map(partnerAddBindingModel, PartnerServiceModel.class));

        return "redirect:/partners/all";
    }

    @ModelAttribute
    PartnerAddBindingModel partnerAddBindingModel() {
        return new PartnerAddBindingModel();
    }


}
