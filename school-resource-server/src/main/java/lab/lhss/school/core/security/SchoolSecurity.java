package lab.lhss.school.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class SchoolSecurity {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getAuthenticatedUsername() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();
        return jwt.getClaim("fullname");
    }


}
