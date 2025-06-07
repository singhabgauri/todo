package first_project.first.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/status")
    public Map<String, Object> getAuthStatus() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && 
                                auth.isAuthenticated() && 
                                !auth.getPrincipal().equals("anonymousUser");
        
        if (isAuthenticated && auth.getPrincipal() instanceof OAuth2User) {
            OAuth2User user = (OAuth2User) auth.getPrincipal();
            return Map.of(
                "authenticated", true,
                "name", user.getAttribute("name")
            );
        }
        
        return Map.of("authenticated", false);
    }
}

