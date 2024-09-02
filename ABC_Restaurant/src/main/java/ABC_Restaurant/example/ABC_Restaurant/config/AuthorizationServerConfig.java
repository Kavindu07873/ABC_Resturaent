package ABC_Restaurant.example.ABC_Restaurant.config;


import ABC_Restaurant.example.ABC_Restaurant.exception.CustomOauthException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

import static ABC_Restaurant.example.ABC_Restaurant.constant.ApplicationConstant.INACTIVE_ACCOUNT;
import static ABC_Restaurant.example.ABC_Restaurant.constant.ApplicationConstant.TEMPORARY_PASSWORD_EXPIRED;
import static ABC_Restaurant.example.ABC_Restaurant.constant.OAuth2TokenConstant.*;

@Log4j2
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .inMemory()
                .withClient(CLIENT_ID)
                .secret(passwordEncoder.encode(CLIENT_SECRET))
//                .secret(passwordEncoder.encode(CLIENT_SECRET))
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS)
                .and()

                .withClient(VENDOR_CLIENT_ID)
                .secret(passwordEncoder.encode(VENDOR_CLIENT_SECRET))
//                .secret(passwordEncoder.encode(CLIENT_SECRET))
                .authorizedGrantTypes(VENDOR_GRANT_TYPE_PASSWORD, VENDOR_AUTHORIZATION_CODE, VENDOR_REFRESH_TOKEN, VENDOR_IMPLICIT)
                .scopes(VENDOR_SCOPE_READ, VENDOR_SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(VENDOR_ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(VENDOR_REFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));

        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(getTokenStore())
                .tokenEnhancer(enhancerChain)
                .accessTokenConverter(accessTokenConverter())
                .exceptionTranslator(e -> {
                    log.info("Auth failed: {}", e.getMessage(), e);
                    if (e.getMessage() != null && e.getMessage().contains("deactivate"))
                        return ResponseEntity.status(INACTIVE_ACCOUNT).body(new CustomOauthException(e.getMessage()));
                    else if (e.getMessage() != null && e.getMessage().contains("expire"))
                        return ResponseEntity.status(TEMPORARY_PASSWORD_EXPIRED).body(new CustomOauthException(e.getMessage()));
                    else
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CustomOauthException(e.getMessage()));
                });
    }

    @Bean
    public JwtTokenStore getTokenStore() {

        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(TOKEN_SIGN_IN_KEY);
        return tokenConverter;

    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhance();
    }
}
