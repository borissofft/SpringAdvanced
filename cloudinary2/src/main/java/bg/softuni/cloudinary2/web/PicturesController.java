package bg.softuni.cloudinary2.web;

import bg.softuni.cloudinary2.model.binding.PictureBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PicturesController {

    @GetMapping("/pictures/add")
    public String addPicture() {
        return "add";
    }

    @PostMapping("/picture/add")
    public String addPicture(PictureBindingModel bindingModel) {
        //TODO
        return "redirect:/pictures/all";
    }

    @GetMapping("/pictures/all")
    public String all(Model model) {
        // TODO all
        return "all";
    }

}
