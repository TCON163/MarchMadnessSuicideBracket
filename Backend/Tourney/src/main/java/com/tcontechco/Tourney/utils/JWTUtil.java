package com.tcontechco.Tourney.utils;


import com.tcontechco.Tourney.exceptions.MalformedTokenException;
import com.tcontechco.Tourney.exceptions.MissingTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Getter @Setter
public class JWTUtil {
    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.prefix}")
    private String prefix;

    @Value("#{24*60*60*1000}")
    private int expiration;

    private Key key;

    @Autowired
    public JWTUtil() {
    }

    @PostConstruct
    private void createKey() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String createJWT(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setIssuer("Nova")
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .signWith(key)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    public Claims parseJWT(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromToken(String token) {
        return parseJWT(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return parseJWT(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Validates a token's authenticity
     *
     * @param token to parse for user information
     * @throws MalformedTokenException token was not created in this session or by this application
     * @throws MissingTokenException token claims are empty
     */
    public void validateToken(String token) throws MalformedTokenException, MissingTokenException {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

        } catch (MalformedJwtException ex) {
            throw new MalformedTokenException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new MalformedTokenException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new MalformedTokenException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new MissingTokenException("JWT claims string is empty.");
        }
    }

    /**
     * Validates a user's presence in the database and if the token is expired
     *
     * @param token to parse for user information
     * @param userDetails details to parse
     * @return true on valid, false on not valid
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
