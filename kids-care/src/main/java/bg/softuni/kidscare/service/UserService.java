package bg.softuni.kidscare.service;

import bg.softuni.kidscare.model.binding.UserApproveBindingModel;
import bg.softuni.kidscare.model.entity.UserEntity;
import bg.softuni.kidscare.model.enums.UserRoleEnum;
import bg.softuni.kidscare.model.service.UserRegisterServiceModel;
import bg.softuni.kidscare.repository.UserRepository;
import bg.softuni.kidscare.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository
            , PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public void registerUser(UserRegisterServiceModel userRegisterServiceModel) {
        if (this.userRepository.count() == 0) {

            UserEntity admin =
                    new UserEntity()
//                    this.modelMapper.map(userRegisterServiceModel, UserEntity.class)
                            .setUsername(userRegisterServiceModel.getUsername())
                            .setEmail(userRegisterServiceModel.getEmail())
                            .setPassword(this.passwordEncoder.encode(userRegisterServiceModel.getPassword()))
                            .setRequestedType(userRegisterServiceModel.getRequestedType())
                            .setRoles(new HashSet<>(this.userRoleRepository.findAll()));

            this.userRepository.save(admin);

        } else {

            UserEntity user = this.modelMapper.map(userRegisterServiceModel, UserEntity.class);
            user.setPassword(this.passwordEncoder.encode(userRegisterServiceModel.getPassword()));

            if ("Клиент".equals(userRegisterServiceModel.getRequestedType().name())) {
                user.setRoles(new HashSet<>(Collections.singleton(this.userRoleRepository.findByRole(UserRoleEnum.NORMAL))));
            }

            this.userRepository.save(user);
        }

    }

    public void approveUser(UserApproveBindingModel userApproveBindingModel) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findByUsername(userApproveBindingModel.getUsername());

        if (userEntityOptional.isPresent()) {
            UserEntity user = userEntityOptional.get()
                    .setRoles(new HashSet<>(Collections.singleton(this.userRoleRepository.findByRole(UserRoleEnum.TEACHER))))
                    .setApproved(true);
            this.userRepository.save(user);
        }

    }
}