package org.launchcode.qleanquotes.controllers.api.v1;

import jakarta.validation.Valid;
import org.launchcode.qleanquotes.models.dto.LoginFormDTO;
import org.launchcode.qleanquotes.models.dto.RegisterFormDTO;
import org.launchcode.qleanquotes.models.dto.api.v1.TokenResponseApiDTO;
import org.launchcode.qleanquotes.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthApiV1Controller {

    @Autowired
    private AuthService authService;

    @PostMapping("/token")
    public ResponseEntity<TokenResponseApiDTO> getToken(@RequestBody LoginFormDTO request) {
        return authService.getToken(request);
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponseApiDTO> register(@RequestBody @Valid RegisterFormDTO request, Errors errors) {
        if (errors.hasErrors()) {
            String errorString = errors.getAllErrors().toString();
            TokenResponseApiDTO error = new TokenResponseApiDTO(errorString);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        return authService.register(request);
    }

}
