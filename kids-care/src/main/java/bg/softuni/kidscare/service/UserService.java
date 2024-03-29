package bg.softuni.kidscare.service;

import bg.softuni.kidscare.model.binding.UserApproveBindingModel;
import bg.softuni.kidscare.model.cmexception.UserNotFoundEx;
import bg.softuni.kidscare.model.entity.UserEntity;
import bg.softuni.kidscare.model.entity.UserRoleEntity;
import bg.softuni.kidscare.model.enums.UserRoleEnum;
import bg.softuni.kidscare.model.service.UserRegisterServiceModel;
import bg.softuni.kidscare.repository.UserRepository;
import bg.softuni.kidscare.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

            UserEntity user = userEntityOptional.get();

            boolean isAdmin = user.getRoles().stream().anyMatch(userRoleEntity -> userRoleEntity.getRole().equals(UserRoleEnum.ADMIN));

            if ("Педагог".equals(user.getRequestedType().name()) && !isAdmin) {

                UserRoleEntity normalRole = this.userRoleRepository.findByRole(UserRoleEnum.NORMAL);
                UserRoleEntity teacherRole = this.userRoleRepository.findByRole(UserRoleEnum.TEACHER);

                user.setRoles(new HashSet<>(Set.of(normalRole, teacherRole)))
                        .setApproved(true);
            }

            this.userRepository.save(user);

        } else {

            throw new UserNotFoundEx(userApproveBindingModel.getUsername());
        }

    }

    public UserEntity findCurrentUser(UserDetails userDetails) {
        return this.userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundEx(userDetails.getUsername()));
    }

    public UserEntity findUserById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundEx(id));
    }
    public UserEntity findUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundEx(username));
    }
}