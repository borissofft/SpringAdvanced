package bg.softuni.securitydemo.service;

import bg.softuni.securitydemo.model.dto.UserRegistrationDto;
import bg.softuni.securitydemo.model.entity.UserEntity;
import bg.softuni.securitydemo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final SecurityContextRepository securityContextRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder
            , UserDetailsService userDetailsService, SecurityContextRepository securityContextRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.securityContextRepository = securityContextRepository;
    }

    public void registerUser(UserRegistrationDto userRegistrationDto,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        UserEntity userEntity = new UserEntity()
                .setFirstName(userRegistrationDto.getFirstName())
                .setLastName(userRegistrationDto.getLastName())
                .setEmail(userRegistrationDto.getEmail())
                .setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

        this.userRepository.save(userEntity);

        var userDetails = this.userDetailsService.loadUserByUsername(userRegistrationDto.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        // populating security context. See SpringSecurityDiagram.jpg
        SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy(); // allows us to create empty SecurityContext

        SecurityContext context = strategy.createEmptyContext();
        context.setAuthentication(authentication);

        strategy.setContext(context);
        this.securityContextRepository.saveContext(context, request, response);
    }

}
