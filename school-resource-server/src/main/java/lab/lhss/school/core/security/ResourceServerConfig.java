package lab.lhss.school.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST, "/student/**").hasAuthority("EDIT_STUDENT")
                .antMatchers(HttpMethod.GET, "/student/**").hasAuthority("SEARCH_STUDENT")
                .antMatchers(HttpMethod.PUT, "/student/**").hasAuthority("CREATE_STUDENT")
                .antMatchers(HttpMethod.DELETE, "/student/**").hasAuthority("DELETE_STUDENT")
                .anyRequest().denyAll()
                .and()
                .cors()
                .and()
                .oauth2ResourceServer()
                    .jwt()
                    .jwtAuthenticationConverter(jwtGrantedAuthoritiesConverter());

        http.csrf().ignoringAntMatchers("/h2-console/**");http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }

    private Converter jwtGrantedAuthoritiesConverter() {
        var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        return jwtGrantedAuthoritiesConverter;
    }

    // Necessary for extract Authorities from jwt array of authorities
    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var authorities = jwt.getClaimAsStringList("authorities");

            if (authorities == null) {
                authorities = Collections.emptyList();
            }

            return authorities.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        });
        return jwtAuthenticationConverter;
    }
}
