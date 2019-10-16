package com.company.JunghoonYoonGameStore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities where username = ?")
                .passwordEncoder(encoder);

    }
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();

        //Authorizations for the main products
        httpSecurity.authorizeRequests()
                    .mvcMatchers(HttpMethod.POST, "/Game").hasAuthority("ROLE_MANAGER")
                    .mvcMatchers(HttpMethod.PUT, "/Game/update").hasAuthority("ROLE_STAFF")
                    .mvcMatchers(HttpMethod.DELETE, "/Game/delete/{gameId}").hasAuthority("ROLE_ADMIN")
                    .anyRequest().permitAll();

        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/Console").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.PUT, "/Console/update").hasAuthority("ROLE_STAFF")
                .mvcMatchers(HttpMethod.DELETE, "/Console/delete/{consoleId}").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll();

        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/TShirt").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.PUT, "/TShirt/update").hasAuthority("ROLE_STAFF")
                .mvcMatchers(HttpMethod.DELETE, "/TShirt/delete/{tShirtId}").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll();

        // Security Measures for Logout
        httpSecurity
                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/allDone").deleteCookies("JSESSIONID").deleteCookies("XSRF-TOKEN")
                .invalidateHttpSession(true);

        httpSecurity.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}
