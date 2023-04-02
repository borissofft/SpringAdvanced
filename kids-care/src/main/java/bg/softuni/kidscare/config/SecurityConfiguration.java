package bg.softuni.kidscare.config;

import bg.softuni.kidscare.model.enums.UserRoleEnum;
import bg.softuni.kidscare.service.ApplicationUserDetailsService;
import bg.softuni.kidscare.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http,
      SecurityContextRepository securityContextRepository) throws Exception {
    http.
        // defines which pages will be authorized
        authorizeHttpRequests().
            // allow access to all static files (images, CSS, js)
          requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
            // the URL-s below are available for all users - logged in and anonymous
          requestMatchers("/", "/psychologists/all", "/articles/**", "/api/**", "/about", "/contacts", "/partners/all"
                  , "/in-progress", "/users/login", "/users/register", "/users/login-error").permitAll().
            // ONLY for clients
          requestMatchers("profiles/all").hasRole(UserRoleEnum.NORMAL.name()).
            // ONLY for teachers
          requestMatchers("/profiles/add").hasRole(UserRoleEnum.TEACHER.name()).
            // ONLY for admins
          requestMatchers("/partners/add", "/psychologists/add", "/articles/add", "/users/approve").hasRole(UserRoleEnum.ADMIN.name()).
        anyRequest().authenticated().
          and().
            // configure login with HTML form
          formLogin().
            loginPage("/users/login").
            // the names of the username, password input fields in the custom login form
            usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
            passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
            // where do we go after login
            defaultSuccessUrl("/"). // use true argument if you always want to go there, otherwise go to previous page
            failureForwardUrl("/users/login-error"). // here need to implement @PostMapping("/users/login-error")
          and().logout(). // configure logout
            logoutUrl("/users/logout").
            logoutSuccessUrl("/"). // go to home page after logout
            invalidateHttpSession(true).
          and().
            securityContext().
            securityContextRepository(securityContextRepository);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return new ApplicationUserDetailsService(userRepository);
  }

  @Bean
  public SecurityContextRepository securityContextRepository() {
    return new DelegatingSecurityContextRepository(
            new RequestAttributeSecurityContextRepository(),
            new HttpSessionSecurityContextRepository()
    );
  }

}
