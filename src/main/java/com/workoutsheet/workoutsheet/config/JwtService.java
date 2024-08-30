package com.workoutsheet.workoutsheet.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${JWT_SECRET}")
    private String JWT_SECRET;

    public String extractTokenEmail(String jwtToken) {
        return extractJwtTokenClaim(jwtToken, Claims::getSubject);
    }

    public String generateJwtToken(UserDetails userDetails) {
        return generateJwtToken(new HashMap<>(), userDetails);
    }

    public String generateJwtToken(
            Map<String, Object> claims,
            UserDetails userDetails
    ) {
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30))
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    public boolean isJwtTokenValid(String jwtToken, UserDetails userDetails) {
        String email = this.extractTokenEmail(jwtToken);
        return email.equals(userDetails.getUsername()) && !isJwtTokenExpired(jwtToken);
    }

    private boolean isJwtTokenExpired(String jwtToken) {
        return this.extractJwtTokenExpiration(jwtToken).before(new Date());
    }

    private Date extractJwtTokenExpiration(String jwtToken) {
        return this.extractJwtTokenClaim(jwtToken, Claims::getExpiration);
    }

    private <T> T extractJwtTokenClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        Claims claims = this.extractAllTokenClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllTokenClaims(String jwtToken) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
