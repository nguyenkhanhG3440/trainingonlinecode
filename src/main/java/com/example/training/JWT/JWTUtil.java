package com.example.training.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    // Khuyến nghị: mã hoá secret bằng Base64
    private final String SECRET = "secret-key-very-secure-secret-key-very-secure"; // ít nhất 32 bytes cho HS256
    private final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(
            java.util.Base64.getEncoder().encodeToString(SECRET.getBytes())
    ));

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {
        try {
            String extractedUsername = extractUsername(token);
            return extractedUsername.equals(username);
        } catch (Exception e) {
            return false;
        }
    }
}
