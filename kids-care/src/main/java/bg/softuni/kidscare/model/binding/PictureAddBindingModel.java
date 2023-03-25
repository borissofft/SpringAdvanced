package bg.softuni.kidscare.model.binding;

import org.springframework.web.multipart.MultipartFile;

public class PictureAddBindingModel {
    private MultipartFile image;

    public PictureAddBindingModel() {

    }

    public MultipartFile getImage() {
        return image;
    }

    public PictureAddBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
