package com.softuni.mobilele.services;

import com.softuni.mobilele.domain.entities.UserEntity;
import com.softuni.mobilele.repositories.RoleRepository;
import com.softuni.mobilele.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements DataBaseInitService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final String defaultAdminPass;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository,
                       @Value("${mobilele.admin.defaultpass}") String defaultAdminPass) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.defaultAdminPass = defaultAdminPass;
    }

    @Override
    public void dbInit() {
        UserEntity admin = new UserEntity()
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setEmail("admin@examle.com")
                .setPassword(passwordEncoder.encode(defaultAdminPass))
                .setRoles(roleRepository.findAll());

        userRepository.save(admin);
    }

    @Override
    public boolean isDbInit() {
        return this.userRepository.count()== 0;
    }

}