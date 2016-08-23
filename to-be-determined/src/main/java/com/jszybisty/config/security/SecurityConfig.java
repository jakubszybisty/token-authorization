package com.jszybisty.config.security;

import com.jszybisty.dao.UserRepository;
import com.jszybisty.rest_api.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableScheduling
@ComponentScan("com.jszybisty.dao")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private String backendAdminRole = "SUPER";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                authorizeRequests().
                antMatchers("/").permitAll().
                antMatchers(actuatorEndpoints()).hasRole(backendAdminRole).
                anyRequest().authenticated().
                and().
                anonymous().disable().
                exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());

        http.addFilterBefore(new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
    }

    private String[] actuatorEndpoints() {
        return new String[]{ApiController.AUTOCONFIG_ENDPOINT, ApiController.BEANS_ENDPOINT, ApiController.CONFIGPROPS_ENDPOINT,
                ApiController.ENV_ENDPOINT, ApiController.MAPPINGS_ENDPOINT,
                ApiController.METRICS_ENDPOINT, ApiController.SHUTDOWN_ENDPOINT};
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthenticationProvider());
    }

    @Autowired
    private UserRepository userRepository;

    @Bean
    public TokenService tokenService() {
        return new TokenService();
    }

    @Bean
    public AuthenticationProvider userAuthenticationProvider() {
        return new UserAuthenticationProvider();
    }

    @Bean
    public AuthenticationProvider tokenAuthenticationProvider() {
        return new TokenAuthenticationProvider(tokenService());
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}