package com.karthik.StudentEntryApp.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Service
public class JWTService {

    String secretKey;

    Map<String, Object> claims = new HashMap<>();

    public JWTService(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGenerator.generateKey();
            this.secretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public String generateToken(String username){

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(Date.from(Instant.ofEpochMilli(System.currentTimeMillis())))
                .expiration(Date.from(Instant.ofEpochMilli(System.currentTimeMillis() + 1000 * 60 * 60 * 3)))
                .and()
                .signWith(getKey())
                .compact();

        //return "dummy-jwt-token-for-" + username;
    }

    Key getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUsername(String token){
        return Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        boolean isUsernameValid = extractUsername(token).equals(userDetails.getUsername());
        boolean isTokenExpired = Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token).getPayload().getExpiration().before(new Date());
        return isUsernameValid && !isTokenExpired;
    }

}
