package bg.softuni.kidscare.service;

import bg.softuni.kidscare.model.entity.ProfileEntity;
import bg.softuni.kidscare.model.service.ProfileServiceModel;
import bg.softuni.kidscare.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final PictureService pictureService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, PictureService pictureService, UserService userService, ModelMapper modelMapper) {
        this.profileRepository = profileRepository;
        this.pictureService = pictureService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    public void addProfile(ProfileServiceModel profileServiceModel) throws IOException {
        long pictureId = this.pictureService.addPicture(profileServiceModel.getImage());
        ProfileEntity profile = this.modelMapper.map(profileServiceModel, ProfileEntity.class);
        profile.setPicture(this.pictureService.findPictureById(pictureId));
        profile.setUser(this.userService.findCurrentUser());
        this.profileRepository.save(profile);
    }
}
