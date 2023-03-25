package bg.softuni.kidscare.web;

import bg.softuni.kidscare.model.binding.ProfileAddBindingModel;
import bg.softuni.kidscare.model.service.ProfileServiceModel;
import bg.softuni.kidscare.service.ProfileService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProfileController(ProfileService profileService, ModelMapper modelMapper) {
        this.profileService = profileService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String add() {
        return "profile-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ProfileAddBindingModel profileAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("profileAddBindingModel", profileAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.profileAddBindingModel",
                            bindingResult);

            return "redirect:add";
        }

        this.profileService
                .addProfile(this.modelMapper.map(profileAddBindingModel, ProfileServiceModel.class));

        return "redirect:all";
    }

    @ModelAttribute
    ProfileAddBindingModel profileAddBindingModel() {
        return new ProfileAddBindingModel();
    }
}
