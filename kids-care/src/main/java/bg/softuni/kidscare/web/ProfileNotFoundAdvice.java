package bg.softuni.kidscare.web;

import bg.softuni.kidscare.model.cmexception.ProfileNotFoundEx;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ProfileNotFoundAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProfileNotFoundEx.class)
    public ModelAndView onProfileNotFound(ProfileNotFoundEx pnfe) {

        ModelAndView modelAndView = new ModelAndView("profile-not-found");
        modelAndView.addObject("id", pnfe.getId());

        return modelAndView;
    }

}
