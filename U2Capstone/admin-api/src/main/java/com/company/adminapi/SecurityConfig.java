package com.company.adminapi;

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

        httpSecurity.authorizeRequests()
                .mvcMatchers("/loggedin").authenticated()
                // ROLE_ADMIN authorizations
                .mvcMatchers(HttpMethod.DELETE, "/customer/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/inventory/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/invoice/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/invoiceitem/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/levelup/*").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/product/*").hasAuthority("ROLE_ADMIN")

                // ROLE_MANAGER authorizations
                .mvcMatchers(HttpMethod.POST, "/customer").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST, "/inventory").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST, "/invoice").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST, "/invoiceitem").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST, "/levelup").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST, "/product").hasAuthority("ROLE_MANAGER")

                // ROLE_TEAMLEAD authorizations
                .mvcMatchers(HttpMethod.PUT, "/customer").hasAuthority("ROLE_TEAMLEAD")
                .mvcMatchers(HttpMethod.PUT, "/invoice").hasAuthority("ROLE_TEAMLEAD")
                .mvcMatchers(HttpMethod.PUT, "/invoiceitem").hasAuthority("ROLE_TEAMLEAD")
                .mvcMatchers(HttpMethod.PUT, "/levelup").hasAuthority("ROLE_TEAMLEAD")
                .mvcMatchers(HttpMethod.PUT, "/product").hasAuthority("ROLE_TEAMLEAD")

                 // ROLE_EMPLOYEE authorizations
                .mvcMatchers(HttpMethod.PUT, "/inventory").hasAuthority("ROLE_EMPLOYEE")

                .mvcMatchers(HttpMethod.GET, "/customer/*").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/inventory/*").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/invoice/*").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/invoiceitem/*").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/levelup/*").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/product/*").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/customer").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/inventory").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/invoice").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/invoiceitem").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/levelup").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/product").hasAuthority("ROLE_EMPLOYEE");

//                .anyRequest().permitAll();
        httpSecurity
                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/allDone")
                .deleteCookies("JSESSIONID")
                .deleteCookies("XSRF-TOKEN")
                .invalidateHttpSession(true);

        httpSecurity
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }


}
