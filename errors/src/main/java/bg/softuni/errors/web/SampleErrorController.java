package bg.softuni.errors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleErrorController {

    @GetMapping("/error")
    public String onError() {
        return "my-error-page";
    }

}
