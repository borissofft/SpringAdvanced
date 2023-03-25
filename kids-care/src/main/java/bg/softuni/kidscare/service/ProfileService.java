package bg.softuni.kidscare.service;

import bg.softuni.kidscare.model.entity.ProfileEntity;
import bg.softuni.kidscare.model.service.ProfileServiceModel;
import bg.softuni.kidscare.model.view.ProfileViewModel;
import bg.softuni.kidscare.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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

    public Page<ProfileViewModel> getAllProfiles(Pageable pageable) {
        return this.profileRepository
                .findAll(pageable)
                .map(this::map);

//        List<ProfileViewModel> viewModels = this.profileRepository.findAll(pageable)
//                .stream()
//                .map(profileEntity -> {
//                    ProfileViewModel viewModel = this.modelMapper.map(profileEntity, ProfileViewModel.class);
//                    viewModel.setPicture(this.pictureService.findPictureById(profileEntity.getPicture().getId()));
//                    viewModel.setUser(this.userService.findUserById(profileEntity.getUser().getId()));
//                    return viewModel;
//                })
//                .toList();
//
//        return new PageImpl<>(viewModels);
    }

    private ProfileViewModel map(ProfileEntity profileEntity) {
        return new ProfileViewModel()
                .setId(profileEntity.getId())
                .setCity(profileEntity.getCity())
                .setAge(profileEntity.getAge())
                .setPhoneNumber(profileEntity.getPhoneNumber())
                .setDescription(profileEntity.getDescription())
                .setPricePerHour(profileEntity.getPricePerHour())
                .setPicture(this.pictureService.findPictureById(profileEntity.getPicture().getId()))
                .setUser(this.userService.findUserById(profileEntity.getUser().getId()));
    }

    public ProfileViewModel findProfileById(Long id) {
        return this.profileRepository.findById(id)
                .map(profileEntity -> this.modelMapper.map(profileEntity, ProfileViewModel.class))
                .orElse(null);
    }
}
