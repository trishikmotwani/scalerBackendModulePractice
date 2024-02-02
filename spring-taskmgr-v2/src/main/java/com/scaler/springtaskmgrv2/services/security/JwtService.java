package com.scaler.springtaskmgrv2.services.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private Algorithm algorithm = Algorithm.HMAC256("your-256-bit-secret");
    public String createJwtToken(Integer userId) {
        String token = JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 1))
                .sign(algorithm);

        return token;
    }
    public Integer getUserIdFromJWT(String jwt) {
        var decodedJWT = JWT.decode(jwt);
        var subject = decodedJWT.getSubject();
        return Integer.parseInt(subject);
    }
    public Integer verifyJwtTokenAndGetUserId(String token) {

        DecodedJWT jwtVerifiedToken = JWT.require(algorithm).build().verify(token);
        String subject = jwtVerifiedToken.getSubject();
        return Integer.parseInt(subject);
    }
}
