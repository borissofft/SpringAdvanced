package com.softuni.mobilele.web;

import com.softuni.mobilele.domain.dtoS.banding.UserRegisterFormDto;
import com.softuni.mobilele.services.UserService;
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
public class RegistrationController {
    private static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register") // post method localhost:8080/users/register
    public String getRegister() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterFormDto userRegisterForm,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegisterForm", userRegisterForm)
                    .addFlashAttribute(BINDING_RESULT_PATH + "userRegisterForm", bindingResult);

            return "redirect:register";
        }

        this.userService.registerUser(userRegisterForm);

        return "redirect:login";
    }


    // Model attributes

    @ModelAttribute(name = "userRegisterForm")
    public UserRegisterFormDto initUserRegisterFormDto() {
        return new UserRegisterFormDto();
    }

}
