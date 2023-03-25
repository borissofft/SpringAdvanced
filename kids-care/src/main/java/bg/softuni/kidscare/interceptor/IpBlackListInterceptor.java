package bg.softuni.kidscare.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
public class IpBlackListInterceptor implements HandlerInterceptor {
    private final List<String> blacklistedIpAddresses = new ArrayList<>();
    private final ThymeleafViewResolver tlvr;

    public IpBlackListInterceptor(ThymeleafViewResolver tlvr) {
//        blacklistedIpAddresses.add("0:0:0:0:0:0:0:1"); // If you want to be blocked at localhost
        this.blacklistedIpAddresses.add("207.142.131.236");
        this.tlvr = tlvr;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = getIpAddressFromRequest(request);
//        System.out.println(ipAddress);
        if (this.blacklistedIpAddresses.contains(ipAddress)) {
            View blockedView = tlvr.resolveViewName("blocked", Locale.getDefault());
            if (blockedView != null) {
                blockedView.render(Map.of(), request, response);
            }
            return false;
        }
        return true;
    }

    private String getIpAddressFromRequest(HttpServletRequest request) {

        String ipAddress = null;

        String xffHeader = request.getHeader("X-Forwarded-For");
        if (xffHeader != null && !xffHeader.equals("unknown")) {
            int commaIdx = xffHeader.indexOf(",");
            if (commaIdx > 0) {
                ipAddress = xffHeader.substring(0, commaIdx - 1);
            }
        }

        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

}
