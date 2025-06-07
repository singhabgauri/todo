// filepath: c:\Users\Lenovo\Downloads\first\first\src\test\java\first_project\first\controller\AuthControllerTest.java
package first_project.first.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenUserNotAuthenticated_thenReturnFalse() throws Exception {
        mockMvc.perform(get("/api/auth/status"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.authenticated").value(false));
    }

    @Test
    public void whenUserAuthenticated_thenReturnTrueAndName() throws Exception {
        // Mock OAuth2User
        OAuth2User oauth2User = mock(OAuth2User.class);
        when(oauth2User.getAttribute("name")).thenReturn("Test User");

        // Mock Authentication
        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(oauth2User);
        when(auth.isAuthenticated()).thenReturn(true);

        // Mock SecurityContext
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        mockMvc.perform(get("/api/auth/status"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.authenticated").value(true))
               .andExpect(jsonPath("$.name").value("Test User"));
    }
}