package com.example.chatapp.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtUtil {

    //TODO key can't be here
    private final Algorithm algorithm = Algorithm.HMAC512("81ffdc50eafb814726e485f548758a939de39c11aa463572e70b6aafed804197");
    private final JWTVerifier verifier = JWT.require(algorithm).build();

    public String createToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(Date.from(Instant.now()))
                .withIssuer("AppChat")
                .withExpiresAt(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        return verifier.verify(token);
    }


    //TODO also check date
    public boolean isTokenValid(String token, String username) {
        return Objects.equals(verifier.verify(token).getSubject(), username);
    }
}
