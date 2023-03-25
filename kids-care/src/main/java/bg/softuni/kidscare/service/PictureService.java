package bg.softuni.kidscare.service;

import bg.softuni.kidscare.model.entity.PictureEntity;
import bg.softuni.kidscare.repository.PictureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PictureService(PictureRepository pictureRepository, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
    }

    public long addPicture(MultipartFile file) throws IOException {
        PictureEntity pictureEntity = new PictureEntity()
                .setFileName(file.getOriginalFilename())
                .setContentType(file.getContentType())
                .setContent(file.getBytes());

        return this.pictureRepository.save(pictureEntity).getId();
    }

    public PictureEntity findPictureById(long pictureId) {
       return this.pictureRepository.findById(pictureId).orElse(null);
    }
}
