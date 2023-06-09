package org.launchcode.qleanquotes.wrappers;

import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.*;
import org.launchcode.qleanquotes.models.PaymentResult;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.squareup.square.Environment.SANDBOX;

@Component
public final class SquareWrapper {

    private static final String SQUARE_ACCESS_TOKEN_ENV_VAR = "SQUARE_ACCESS_TOKEN";

    // The environment variable containing a Square application ID.
    // This must be set in order for the application to start.
    private static final String SQUARE_APP_ID_ENV_VAR = "SQUARE_APPLICATION_ID";

    // The environment variable containing a Square location ID.
    // This must be set in order for the application to start.
    private static final String SQUARE_LOCATION_ID_ENV_VAR = "SQUARE_LOCATION_ID";

    // The environment variable indicate the square environment - sandbox or
    // production.
    // This must be set in order for the application to start.
//    private static final String SQUARE_ENV_ENV_VAR = "ENVIRONMENT";

    private final com.squareup.square.SquareClient squareClient;
    private final String squareLocationId;
    private final String squareAppId;
    private final String squareEnvironment;
    private final PaymentsApi paymentsApi;

    public SquareWrapper() throws ApiException {
        squareEnvironment = SANDBOX.toString();
        squareAppId = "sandbox-sq0idb-VMvJ-CvdQE73OtQbS6uH_Q";
        squareLocationId = "LPA9X767FQCCV";

        squareClient = new com.squareup.square.SquareClient.Builder()
                .environment(SANDBOX)
                .accessToken("EAAAEMaLGtOCNqh3giwdE5Blj4FyIciGfc7gUGUGN9vvvsey8gb1vsWdESceuX53")
                .build();

        paymentsApi = squareClient.getPaymentsApi();
    }

    private String mustLoadEnvironmentVariable(String name) {
        String value = System.getenv(name);
        if (value == null || value.length() == 0) {
            throw new IllegalStateException(
                    String.format("The %s environment variable must be set", name));
        }

        return value;
    }

    public PaymentResult createPayment(
            final CreatePaymentRequest body) throws ApiException, IOException {
        PaymentsApi paymentsApi = squareClient.getPaymentsApi();
        paymentsApi.createPayment(body);
        return new PaymentResult("SUCCESS", null);
    }

    public String getSquareLocationId() {
        return squareLocationId;
    }

    public String getSquareAppId() {
        return squareAppId;
    }

    public String getSquareEnvironment() {
        return squareEnvironment;
    }

    public PaymentsApi getPaymentsApi() {
        return paymentsApi;
    }
}
