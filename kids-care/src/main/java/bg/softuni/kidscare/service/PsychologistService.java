package bg.softuni.kidscare.service;

import bg.softuni.kidscare.model.entity.PsychologistEntity;
import bg.softuni.kidscare.model.service.PsychologistServiceModel;
import bg.softuni.kidscare.model.view.PsychologistViewModel;
import bg.softuni.kidscare.repository.PsychologistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PsychologistService {
    private final PsychologistRepository psychologistRepository;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;

    @Autowired
    public PsychologistService(PsychologistRepository psychologistRepository, PictureService pictureService, ModelMapper modelMapper) {
        this.psychologistRepository = psychologistRepository;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
    }


    public void addPsychologists(PsychologistServiceModel psychologistServiceModel) throws IOException {
        long pictureId = this.pictureService.addPicture(psychologistServiceModel.getImage());
        PsychologistEntity psychologist = this.modelMapper.map(psychologistServiceModel, PsychologistEntity.class);
        psychologist.setPicture(this.pictureService.findPictureById(pictureId));
        this.psychologistRepository.save(psychologist);
    }

    public List<PsychologistViewModel> findAllPsychologists() {
        return this.psychologistRepository.findAll()
                .stream()
                .map(psychologistEntity -> {
                    PsychologistViewModel viewModel = this.modelMapper.map(psychologistEntity, PsychologistViewModel.class);
                    viewModel.setPicture(this.pictureService.findPictureById(psychologistEntity.getPicture().getId()));
                    return viewModel;
                })
                .toList();
    }

//    public long deleteById(Long id) {
//        Optional<PsychologistEntity> psychologist = this.psychologistRepository.findById(id);
//        psychologist.ifPresent(this.psychologistRepository::delete);
//        return id;
//    }

    public void deleteById(Long id) {
        PsychologistEntity psychologist = this.psychologistRepository
                .findById(id).orElseThrow();
    }
}
