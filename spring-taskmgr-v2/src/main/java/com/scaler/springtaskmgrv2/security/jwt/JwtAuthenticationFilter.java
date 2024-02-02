package com.scaler.springtaskmgrv2.security.jwt;

import com.scaler.springtaskmgrv2.services.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

public class JwtAuthenticationFilter extends AuthenticationFilter {

    public JwtAuthenticationFilter() {
        super(new JwtAuthenticationManager(), new JwtAuthenticationConverter());

        setSuccessHandler((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
    }

    static class JwtAuthenticationConverter implements AuthenticationConverter {

        @Override
        public Authentication convert(HttpServletRequest request) {

            if(request.getHeader("Authorization") != null) {
                String[] authHeaderValues = request.getHeader("Authorization").split(" ");
                if(authHeaderValues.length != 2) {
                    return null;
                }
                String token = authHeaderValues[1];
                return new JwtAuthentication(token);
            }
            return null;
        }
    }

    static class JwtAuthenticationManager implements AuthenticationManager {

        @Autowired
        private JwtService jwtService;
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            // just sanity check
            if(authentication instanceof JwtAuthentication) {
                var jwtAuthentication = (JwtAuthentication) authentication;

                if(jwtAuthentication.getCredentials() != null) {
                    Integer userId = jwtService.verifyJwtTokenAndGetUserId(jwtAuthentication.getCredentials());
                    jwtAuthentication.setUserId(userId);
                    return jwtAuthentication;
                }
            }
            return null;
        }
    }
}
