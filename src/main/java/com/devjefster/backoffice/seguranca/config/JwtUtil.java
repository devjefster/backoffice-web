package com.devjefster.backoffice.seguranca.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component

public class JwtUtil {

    @Value("${security.jwt.secret}")
    protected String secretKey;

    @Value("${security.jwt.issuer}")
    protected String issuer;

    @Value("${security.jwt.expiration-time}")
    protected String expirationTime;

    public boolean validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);

            if (isTokenExpired(claims)) {
                throw new IllegalStateException("Token is expired");
            }

            if (!issuer.equals(claims.getIssuer())) {
                throw new IllegalStateException("Invalid token issuer");
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> extractRoles(String token) {

        return Collections.singletonList(extractAllClaims(token).get("role", String.class));
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    protected Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    protected SecretKey getSigningKey() {
        byte[] keyBytes = this.secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }


}
