package bg.softuni.kidscare.service;

import bg.softuni.kidscare.model.cmexception.ProfileNotFoundEx;
import bg.softuni.kidscare.model.entity.ProfileEntity;
import bg.softuni.kidscare.model.enums.UserRoleEnum;
import bg.softuni.kidscare.model.service.ProfileServiceModel;
import bg.softuni.kidscare.model.view.ProfileViewModel;
import bg.softuni.kidscare.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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


    public void addProfile(ProfileServiceModel profileServiceModel, UserDetails userDetails) throws IOException {
        long pictureId = this.pictureService.addPicture(profileServiceModel.getImage());
        ProfileEntity profile = this.modelMapper.map(profileServiceModel, ProfileEntity.class);
        profile.setPicture(this.pictureService.findPictureById(pictureId));
        profile.setUser(this.userService.findCurrentUser(userDetails));
        this.profileRepository.save(profile);
    }

    public Page<ProfileViewModel> getAllProfiles(Pageable pageable) {
        return this.profileRepository
                .findAll(pageable)
                .map(this::map);
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
                .orElseThrow(() -> new ProfileNotFoundEx(id));
    }

    public void deleteProfileById(Long id) {
        this.profileRepository.findById(id)
                .ifPresent(this.profileRepository::delete);
    }

    public boolean isOwner(UserDetails userDetails, Long id) {
        if (id == null || userDetails == null) {
            return  false;
        }

        ProfileEntity profile = this.profileRepository.
                findById(id).
                orElse(null);

        if (profile == null) {
            return false;
        }

        return userDetails.getUsername().equals(profile.getUser().getUsername()) ||
                isUserAdmin(userDetails);
    }

    private boolean isUserAdmin(UserDetails userDetails) {
        return userDetails.getAuthorities().
                stream().
                map(GrantedAuthority::getAuthority).
                anyMatch(a -> a.equals("ROLE_" + UserRoleEnum.ADMIN.name()));
    }

}
