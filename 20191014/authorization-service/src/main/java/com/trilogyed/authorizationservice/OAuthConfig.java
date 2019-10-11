package com.trilogyed.authorizationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuthConfig  extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;

    @Autowired
    public OAuthConfig(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("html5")
                .authorizedGrantTypes("password")
                .scopes("ROLE_LVL1", "ROLE_LVL2")
                .secret("$2a$10$fgmCFkI4bjLLkb/Nr8gQaOP9Mnja2gZTPxqQLPFQ5E8j8I5VdwfVO");

//        clients.inMemory()
//                .withClient("Android")
//                .authorizedGrantTypes("password")
//                .scopes("ROLE_LVL1", "ROLE_LVL2", "ROLE_ADMIN")
//                .secret("$2a$10$Cl/VImPvkmKWuL/CDYnhTuTW7UAJPAPM/ORpy.mG0.V4iU9GFSJwu");
//
//        clients.inMemory()
//                .withClient("iPhone")
//                .authorizedGrantTypes("password")
//                .scopes("ROLE_LVL1", "ROLE_LVL2", "ROLE_ADMIN")
//                .secret("$2a$10$vLO.MpSdUHlBB3BTSpos9edK9kg.G2hsm5Jffo4lwCBGTGxsYAbSa");
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
    }
}