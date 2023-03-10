package bg.softuni.errors.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@Configuration
public class ErrorConfig {

    @Bean
    public HandlerExceptionResolver simpleMappingExceptionResolver() {

        SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty(NullPointerException.class.getSimpleName(), "npe"); // map exception to View npe.html
        smer.setExceptionMappings(properties);

        return smer;
    }

//    @Bean
//    public HandlerExceptionResolver customHandlerExceptionResolver() {
//        return new HandlerExceptionResolver() {
//            @Override
//            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//                if (ex instanceof NullPointerException) {
//                    ModelAndView modelAndView = new ModelAndView("npe");
//                    return modelAndView;
//                }
//                return null;
//            }
//        };
//    }

}
