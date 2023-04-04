package bg.softuni.kidscare.web;

import bg.softuni.kidscare.model.binding.ProfileAddBindingModel;
import bg.softuni.kidscare.model.service.ProfileServiceModel;
import bg.softuni.kidscare.model.view.ProfileViewModel;
import bg.softuni.kidscare.service.ProfileService;
import bg.softuni.kidscare.util.ImageUtil;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;
    private final ImageUtil imageUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public ProfileController(ProfileService profileService, ImageUtil imageUtil, ModelMapper modelMapper) {
        this.profileService = profileService;
        this.imageUtil = imageUtil;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String add() {
        return "profile-add";
    }

    @GetMapping("/{id}")
    public String getProfileById(@PathVariable Long id,
                                 @AuthenticationPrincipal UserDetails userDetails,
                                 Model model) {

        model.addAttribute("profile", this.profileService.findProfileById(id));
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("canDelete", this.profileService.isOwner(userDetails, id));

        return "profile-details";
    }

    @GetMapping("/all")
    public String allProfiles(Model model, @PageableDefault(sort = "id", size = 3) Pageable pageable) {
        Page<ProfileViewModel> allProfilesPageable = this.profileService.getAllProfiles(pageable);
        model.addAttribute("profiles", allProfilesPageable);
        model.addAttribute("imgUtil", new ImageUtil());

        return "profiles";
    }

    @PreAuthorize("@profileService.isOwner(#userDetails, #id)")
    @DeleteMapping("/{id}")
    public String deleteProfile(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {

        this.profileService.deleteProfileById(id);

        return "redirect:all?page=0";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ProfileAddBindingModel profileAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetails userDetails) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("profileAddBindingModel", profileAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.profileAddBindingModel",
                            bindingResult);

            return "redirect:/profiles/add";
        }

        this.profileService
                .addProfile(this.modelMapper.map(profileAddBindingModel, ProfileServiceModel.class),
                        userDetails);

        return "redirect:all?page=0";
    }

    @ModelAttribute
    ProfileAddBindingModel profileAddBindingModel() {
        return new ProfileAddBindingModel();
    }
}
