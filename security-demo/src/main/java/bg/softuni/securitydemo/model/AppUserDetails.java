package bg.softuni.securitydemo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AppUserDetails extends User { // This class will help us to get some fields of the UserEntity and use it where needed(thymeleaf...)
    private String country; // In this example we will get fields country, firstName and lastName from the UserEntity
    private String fullName;

    public AppUserDetails(String username, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getCountry() {
        return country;
    }

    public AppUserDetails setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public AppUserDetails setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
