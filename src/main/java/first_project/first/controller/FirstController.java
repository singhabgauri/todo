package first_project.first.controller;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    
    @GetMapping("/")
    public String index(){
        return "forward:/index.html";
    }

    @GetMapping("/login/success")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User principal){
        return "redirect:/user/dashboard";  // Redirect to dashboard after login
    }

    @GetMapping("/user/dashboard")
    public String userDashboard(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return "redirect:/";
        }
        return "forward:/user/dashboard.html";
    }
}
