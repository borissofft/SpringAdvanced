package bg.softuni.kidscare.web;

import bg.softuni.kidscare.model.cmexception.UserNotFoundEx;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class UserNotFoundAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundEx.class)
    public ModelAndView onUserNotFound(UserNotFoundEx unfe) {

        ModelAndView modelAndView = new ModelAndView("user-not-found");
        modelAndView.addObject("username", unfe.getUsername());
        modelAndView.addObject("id", unfe.getId());

        return modelAndView;
    }

}
