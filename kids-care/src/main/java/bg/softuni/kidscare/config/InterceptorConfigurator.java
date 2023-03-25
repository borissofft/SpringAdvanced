package bg.softuni.kidscare.config;

import bg.softuni.kidscare.interceptor.IpBlackListInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfigurator implements WebMvcConfigurer {

    private final IpBlackListInterceptor ipBlackListInterceptor;

    @Autowired
    public InterceptorConfigurator(IpBlackListInterceptor ipBlackListInterceptor) {
        this.ipBlackListInterceptor = ipBlackListInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.ipBlackListInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
