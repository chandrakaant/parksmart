package com.highpeak.parksmart.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Chandrakant Patel on 7/3/18.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * Method to Configure security endpoints
     *
     * @param http
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()

                .antMatchers("/rest/users/login").permitAll()

                .antMatchers("/rest/users/forgotPassword").permitAll()

                .antMatchers("/rest/users/setPassword").permitAll()

                .antMatchers("/swagger-resources/configuration/ui").permitAll()
                .anyRequest().authenticated()

                .and()

                .addFilterBefore(new JWTAuthenticationFilter("/rest/users/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                //                .addFilterBefore(new AdminAuthenticationFilter("/rest/admin/login", authenticationManager()),
                //
                //                        UsernamePasswordAuthenticationFilter.class)

                .addFilterBefore(new JWTAuthorizationFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }

    /**
     * Method to Configure
     *
     * @param auth
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
            auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure( WebSecurity web ) throws Exception
    {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/configuration/security", "/swagger-resources",
                "/configuration/security", "/swagger-ui.html", "/webjars/**", "/notification/sendOTP,/swagger-resources/configuration/ui");

    }

}