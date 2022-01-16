package com.andile.polls.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private String jwtExpirationInMs;

    /**
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    /**
     * @param token
     * @return
     */
    public Long getUserIdFromJWT(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    /**
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJwt(token);
            return true;
        }catch (SignatureException exception){
            logger.error("Invalid JWT signature");
        }catch (MalformedJwtException exception){
            logger.error("Invalid JWT token");
        }catch (ExpiredJwtException exception){
            logger.error("Expired JWT token");
        }catch (UnsupportedJwtException exception){
            logger.error("Unsupported JWT token");
        }catch (IllegalArgumentException exception){
            logger.error("JWT claims string is empty");
        }
        return false;
    }
}
