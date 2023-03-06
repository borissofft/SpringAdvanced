package bg.softuni.cloudinary2.web;

import bg.softuni.cloudinary2.model.binding.PictureBindingModel;
import bg.softuni.cloudinary2.model.entity.PictureEntity;
import bg.softuni.cloudinary2.model.view.PictureViewModel;
import bg.softuni.cloudinary2.repository.PictureRepository;
import bg.softuni.cloudinary2.service.CloudinaryImage;
import bg.softuni.cloudinary2.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class PicturesController {
    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;

    @Autowired
    public PicturesController(CloudinaryService cloudinaryService, PictureRepository pictureRepository) {
        // Do not use repos directly in the controller
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
    }

    @GetMapping("/pictures/add")
    public String addPicture() {
        return "add";
    }

    @PostMapping("/pictures/add")
    public String addPicture(PictureBindingModel bindingModel) throws IOException {

        var picture = createPictureEntity(bindingModel.getPicture(), bindingModel.getTitle());

        this.pictureRepository.save(picture);

        return "redirect:/pictures/all";
    }

    private PictureEntity createPictureEntity(MultipartFile file, String title) throws IOException {
        CloudinaryImage upload = this.cloudinaryService.upload(file);
        return new PictureEntity()
                .setPublicId(upload.getPublicId())
                .setTitle(title)
                .setUrl(upload.getUrl());
    }

    @Transactional
    @DeleteMapping("/pictures/delete")
    public String delete(@RequestParam("public_id") String publicId) {

        if (this.cloudinaryService.delete(publicId)) {
            this.pictureRepository.deleteAllByPublicId(publicId);
        }

        return "redirect:/pictures/all";
    }

    @GetMapping("/pictures/all")
    public String all(Model model) {
        List<PictureViewModel> pictures = this.pictureRepository
                .findAll()
                .stream()
                .map(this::asViewModel)
                .toList();

        model.addAttribute("pictures", pictures);

        return "all";
    }

    private PictureViewModel asViewModel(PictureEntity picture) {
        return new PictureViewModel()
                .setTitle(picture.getTitle())
                .setUrl(picture.getUrl())
                .setPublicId(picture.getPublicId());
    }

}

// http://localhost:8080/h2-console - access to DB