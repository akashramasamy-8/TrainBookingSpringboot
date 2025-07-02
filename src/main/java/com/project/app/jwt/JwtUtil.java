package com.project.app.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY_STRING="fbHJOsHTX9L0EUudM0PYt7VhtVB5hBWT";

    private final SecretKey SECRET_KEY= Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
    public String generateToken(UserDetails userDetails,Long userId){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .claim("userId",userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000 *60 * 60))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact()
                ;
    }

    public boolean validateToken(String token, UserDetails userDetails){
        return extractUsername(token).equals(userDetails.getUsername());
    }
    public String extractUsername(String token){
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
