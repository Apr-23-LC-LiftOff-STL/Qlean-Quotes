package org.launchcode.qleanquotes.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.launchcode.qleanquotes.models.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private static final int EXPIRE_DURATION = 14; // 14 days

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    // non-working method
    // https://www.codejava.net/frameworks/spring-boot/spring-security-jwt-authentication-tutorial#JwtUtil
    public String generateToken(Customer customer) {
        return Jwts
                .builder()
                .setSubject(customer.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // https://medium.com/swlh/stateless-jwt-authentication-with-spring-boot-a-better-approach-1f5dbae6c30f
    public String createJwt(String subject, Map<String, String> claims) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Instant.now().toEpochMilli());
        calendar.add(Calendar.DATE, EXPIRE_DURATION);

        JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);

        // Add claims
        claims.forEach(jwtBuilder::withClaim);

        return jwtBuilder
                .withNotBefore(new Date())
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

}
