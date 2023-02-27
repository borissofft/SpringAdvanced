package bg.softuni.securitydemo.config;

import bg.softuni.securitydemo.model.enums.UserRoleEnum;
import bg.softuni.securitydemo.repository.UserRepository;
import bg.softuni.securitydemo.service.ApplicationUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.
        // defines which pages will be authorized
        authorizeHttpRequests().
            // allow access to all static files (images, CSS, js)
          requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
            // the URL-s below are available for all users - logged in and anonymous
          requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll().
            // only for moderators
          requestMatchers("/pages/moderators").hasRole(UserRoleEnum.MODERATOR.name()).
            // only for admins
          requestMatchers("/pages/admins").hasRole(UserRoleEnum.ADMIN.name()).
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
            invalidateHttpSession(true);

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

}
