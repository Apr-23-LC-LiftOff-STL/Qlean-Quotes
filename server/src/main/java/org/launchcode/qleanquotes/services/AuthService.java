package org.launchcode.qleanquotes.services;

import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.launchcode.qleanquotes.models.dto.LoginFormDTO;
import org.launchcode.qleanquotes.models.dto.RegisterFormDTO;
import org.launchcode.qleanquotes.models.dto.api.v1.TokenResponseApiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtService jwtService;

    public ResponseEntity<TokenResponseApiDTO> getToken(LoginFormDTO request) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail(),
                                    request.getPassword()
                            )
                    );

            Customer customer = (Customer) authentication.getPrincipal();
//            String token = jwtService.generateToken(customer);

            Map<String, String> claims = new HashMap<>();
            String authorities = customer.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
            claims.put("authorities", authorities);
            claims.put("userId", String.valueOf(customer.getId()));

            String token = jwtService.createJwt(customer.getEmail(), claims);
            TokenResponseApiDTO response = new TokenResponseApiDTO(token);

            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            TokenResponseApiDTO error = new TokenResponseApiDTO(new Error(ex));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    public ResponseEntity<TokenResponseApiDTO> register(RegisterFormDTO request) {
        String password = request.getPassword();
        String verifyPassword = request.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            TokenResponseApiDTO error = new TokenResponseApiDTO(new Error("Passwords do not match. Please try again."));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        Customer existingCustomer = customerRepository.findByEmail(request.getEmail());
        if (existingCustomer != null) {
            TokenResponseApiDTO error = new TokenResponseApiDTO(new Error("Customer already exists with that email."));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        try {
            Customer newCustomer = new Customer(
                    request.getName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPassword());
            customerRepository.save(newCustomer);

            String token = jwtService.generateToken(newCustomer);
            TokenResponseApiDTO response = new TokenResponseApiDTO(token);

            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            TokenResponseApiDTO error = new TokenResponseApiDTO(new Error(ex));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

}
