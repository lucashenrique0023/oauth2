package lab.lhss.shcoolauthserver.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // Enabling Client(s) to receive access tokens
        clients.inMemory()
                .withClient("schoolclient")
                    .secret(passwordEncoder.encode("school123"))
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("write", "read")
                    .accessTokenValiditySeconds(20)
                    .refreshTokenValiditySeconds(60 * 60) // 1 Hour
                .and()
                    .withClient("backend-client")
                    .secret(passwordEncoder.encode("backendclient123"))
                    .authorizedGrantTypes("client_credentials")
                    .scopes("write", "read")
                .and()
                    .withClient("schoolanalytics")
                    .secret(passwordEncoder.encode("school123"))
                    .authorizedGrantTypes("authorization_code")
                    .scopes("write", "read")
                    .redirectUris("http://school-client-app")
                .and()
                    .withClient("schooladmin")
                    .authorizedGrantTypes("implicit")
                    .scopes("write", "read")
                    .redirectUris("http://school-client2-app")
                .and()
                    .withClient("checktoken")
                    .secret(passwordEncoder.encode("check123"));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // isAuthenticated is a Spring Security Expression
        // Only Authenticated can check tokens. (use permitAll() for not authenticated)
        security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // Only for Password Flow, to analyze Resource Owner credentials
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .reuseRefreshTokens(false)
                .accessTokenConverter(jwtAccessTokenConverter())
                .tokenGranter(tokenGranter(endpoints));
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("SECRET-SYMMETRIC-KEY");

        return jwtAccessTokenConverter;
    }

    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        var pkceAuthorizationCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(),
                endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory());

        var granters = Arrays.asList(
                pkceAuthorizationCodeTokenGranter, endpoints.getTokenGranter());

        return new CompositeTokenGranter(granters);
    }
}
