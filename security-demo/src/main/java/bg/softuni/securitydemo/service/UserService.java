package bg.softuni.securitydemo.service;

import bg.softuni.securitydemo.model.dto.UserRegistrationDto;
import bg.softuni.securitydemo.model.entity.UserEntity;
import bg.softuni.securitydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder
            , UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public void registerUser(UserRegistrationDto userRegistrationDto,
                             Consumer<Authentication> successfulLoginProcessor) {
        UserEntity userEntity = new UserEntity()
                .setFirstName(userRegistrationDto.getFirstName())
                .setLastName(userRegistrationDto.getLastName())
                .setEmail(userRegistrationDto.getEmail())
                .setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

        // TODO here we have to add Role to the userEntity if we want to have... Implement it in the project!
        this.userRepository.save(userEntity);

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userRegistrationDto.getEmail());

        // See SpringSecurityDiagram.jpg
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, // Principal
                userDetails.getPassword(), // Credentials
                userDetails.getAuthorities() // Authorities (List<GrantedAuthorities>)
        );

        successfulLoginProcessor.accept(authentication);

    }

}
