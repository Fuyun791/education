package com.education.until;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author dell
 */
public class JwtTokenUtil {

    public static String generateToken(String subject, int expirationSeconds) {
        String jwtBuilder= Jwts.builder()
                .setClaims(null)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(SignatureAlgorithm.HS512, "education")
                .compact();
        System.out.println(new Date(System.currentTimeMillis() + expirationSeconds * 1000));
        return jwtBuilder;
    }

    public static String parseToken(String token) {
        String subject = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("education")
                    .parseClaimsJws(token).getBody();
            subject = claims.getSubject();
        } catch (Exception e) {
        }
        return subject;
    }
}
