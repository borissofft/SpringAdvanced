package bg.softuni.kidscare.model.cmexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User was not found.") // Status code 404
public class UserNotFoundEx extends RuntimeException {

    private Long id;
    private String username;

    public UserNotFoundEx(String username) {
        super("User with username " + username + " not found!");
        this.username = username;
    }

    public UserNotFoundEx(Long id) {
        super("User with id " + id + " not found!");
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public UserNotFoundEx setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserNotFoundEx setUsername(String username) {
        this.username = username;
        return this;
    }
}
