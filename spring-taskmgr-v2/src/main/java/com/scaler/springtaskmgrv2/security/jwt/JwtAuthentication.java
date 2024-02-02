package com.scaler.springtaskmgrv2.security.jwt;

import com.auth0.jwt.JWT;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class JwtAuthentication implements Authentication {

    private String token;
    private Integer userId;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    JwtAuthentication(String token) {
        this.token = token;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public boolean isAuthenticated() {
        return userId != null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
