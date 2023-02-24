package bg.softuni.securitydemo.service;

import bg.softuni.securitydemo.model.entity.UserEntity;
import bg.softuni.securitydemo.model.entity.UserRoleEntity;
import bg.softuni.securitydemo.model.enums.UserRoleEnum;
import bg.softuni.securitydemo.repository.UserRepository;
import bg.softuni.securitydemo.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InitService {

  private UserRoleRepository userRoleRepository;
  private UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public InitService(UserRoleRepository userRoleRepository,
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      @Value("${app.default.password}") String defaultPassword) {
    this.userRoleRepository = userRoleRepository;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @PostConstruct
  public void init() {
    initRoles();
    initUsers();
  }

  private void initRoles() {
    if (this.userRoleRepository.count() == 0) {
      var moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
      var adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

      this.userRoleRepository.save(moderatorRole);
      this.userRoleRepository.save(adminRole);
    }
  }

  private void initUsers() {
    if (this.userRepository.count() == 0) {
      initAdmin();
      initModerator();
      initNormalUser();
    }
  }

  private void initAdmin(){
    var adminUser = new UserEntity().
        setEmail("admin@example.com").
        setFirstName("Admin").
        setLastName("Adminov").
        setPassword(this.passwordEncoder.encode("topsecret")).
        setRoles(this.userRoleRepository.findAll());

    this.userRepository.save(adminUser);
  }

  private void initModerator(){

    var moderatorRole = this.userRoleRepository.
        findUserRoleEntityByRole(UserRoleEnum.MODERATOR).orElseThrow();

    var moderatorUser = new UserEntity().
        setEmail("moderator@example.com").
        setFirstName("Moderator").
        setLastName("Moderatorov").
        setPassword(this.passwordEncoder.encode("topsecret")).
        setRoles(List.of(moderatorRole));

    this.userRepository.save(moderatorUser);
  }

  private void initNormalUser(){

    var normalUser = new UserEntity().
        setEmail("user@example.com").
        setFirstName("User").
        setLastName("Userov").
        setPassword(this.passwordEncoder.encode("topsecret"));

    this.userRepository.save(normalUser);
  }
}
