package bg.softuni.kidscare.web;

import bg.softuni.kidscare.model.binding.UserApproveBindingModel;
import bg.softuni.kidscare.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserApproveController {
    private final UserService userService;

    @Autowired
    public UserApproveController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/approve")
    public String approve() {
        return "user-approve";
    }

    @PostMapping("/approve")
    public String registerConfirm(@Valid UserApproveBindingModel userApproveBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("userApproveBindingModel", userApproveBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userApproveBindingModel",
                            bindingResult);

            return "redirect:approve";
        }

        this.userService.approveUser(userApproveBindingModel);

        return "redirect:/";
    }

    @ModelAttribute
    UserApproveBindingModel userApproveBindingModel() {
        return new UserApproveBindingModel();
    }

}
