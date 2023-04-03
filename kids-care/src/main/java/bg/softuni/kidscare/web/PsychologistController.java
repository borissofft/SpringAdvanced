package bg.softuni.kidscare.web;

import bg.softuni.kidscare.model.binding.PsychologistAddBindingModel;
import bg.softuni.kidscare.model.service.PsychologistServiceModel;
import bg.softuni.kidscare.model.view.PsychologistViewModel;
import bg.softuni.kidscare.service.PsychologistService;
import bg.softuni.kidscare.util.ImageUtil;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/psychologists")
public class PsychologistController {
    private final PsychologistService psychologistService;
    private ImageUtil imageUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public PsychologistController(PsychologistService psychologistService, ImageUtil imageUtil, ModelMapper modelMapper) {
        this.psychologistService = psychologistService;
        this.imageUtil = imageUtil;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add() {
        return "psychologist-add";
    }

    @GetMapping("/all")
    public String allPsychologists(Model model) {
        List<PsychologistViewModel> psychologistViewModels = this.psychologistService.findAllPsychologists();
        model.addAttribute("psychologists", psychologistViewModels);
        model.addAttribute("imgUtil", new ImageUtil());
        return "psychologists";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid PsychologistAddBindingModel psychologistAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("psychologistAddBindingModel", psychologistAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.psychologistAddBindingModel",
                            bindingResult);

            return "redirect:/psychologists/add";
        }

        this.psychologistService
                .addPsychologists(this.modelMapper.map(psychologistAddBindingModel, PsychologistServiceModel.class));

        return "redirect:psychologists/all";
    }

    @ModelAttribute
    PsychologistAddBindingModel psychologistAddBindingModel() {
        return new PsychologistAddBindingModel();
    }

}
