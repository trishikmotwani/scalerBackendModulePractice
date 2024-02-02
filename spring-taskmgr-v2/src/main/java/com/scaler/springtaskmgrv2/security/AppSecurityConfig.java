package com.scaler.springtaskmgrv2.security;

import com.scaler.springtaskmgrv2.security.jwt.JwtAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;


// Web security configurer adapter is deprecated
// Use a org.springframework.security.web.SecurityFilterChain Bean to configure HttpSecurity
// or a WebSecurityCustomizer Bean to configure WebSecurity.
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().cors().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // httpSecurity.authorizeRequests().anyRequest().permitAll();
        httpSecurity.authorizeRequests().antMatchers("/users/auth/**").permitAll()
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(new JwtAuthenticationFilter(), AnonymousAuthenticationFilter.class);
    }

    // newer way of doing security and replacing extends WebSecurityConfigurerAdapter
    /*@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/auth/login", "/docs/**", "/users").permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                );

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }*/
}
