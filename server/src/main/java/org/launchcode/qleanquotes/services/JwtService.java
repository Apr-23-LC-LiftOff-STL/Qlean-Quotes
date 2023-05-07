package org.launchcode.qleanquotes.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.launchcode.qleanquotes.models.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hours in milliseconds

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(Customer customer) {
        return Jwts
                .builder()
                .setSubject(customer.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(SECRET_KEY))
                .compact();
    }

}
