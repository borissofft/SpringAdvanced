package bg.softuni.securitydemo.web;

import bg.softuni.securitydemo.model.dto.UserRegistrationDto;
import bg.softuni.securitydemo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserRegistrationController {

    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String registerNewUser(@RequestBody UserRegistrationDto userRegistrationDto,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        return "auth-register";
    }


}
