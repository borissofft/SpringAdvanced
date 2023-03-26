package bg.softuni.kidscare.model.cmexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Profile was not found.") // Status code 404
public class ProfileNotFoundEx extends RuntimeException {

    private Long id;

    public ProfileNotFoundEx(Long id) {
        super("Profile with id " + id + " not found!");
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public ProfileNotFoundEx setId(Long id) {
        this.id = id;
        return this;
    }
}
