package org.launchcode.qleanquotes.models.dto.api.v1;

public class TokenResponseApiDTO {

    private String token;

    private String error;

    public TokenResponseApiDTO(String token) {
        this.token = token;
    }

    public TokenResponseApiDTO(Error error) {
        this.error = error.getMessage();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
