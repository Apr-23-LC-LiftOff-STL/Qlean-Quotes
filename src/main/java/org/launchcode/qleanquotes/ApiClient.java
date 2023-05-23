package org.launchcode.qleanquotes;

import com.squareup.square.Environment;
import com.squareup.square.SquareClient;
import com.squareup.square.api.CustomersApi;
import com.squareup.square.api.LocationsApi;
import com.squareup.square.api.OrdersApi;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;

import java.io.IOException;

public class ApiClient {

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
    private static final String SQUARE_ENV_ENV_VAR = "ENVIRONMENT";

    private final String squareLocationId;
    private final String squareAppId;
    private final String squareEnvironment;
    private PaymentsApi paymentsApi;
    public SquareClient squareClient;

    public ApiClient() throws ApiException {
        squareEnvironment = mustLoadEnvironmentVariable(SQUARE_ENV_ENV_VAR);
        squareAppId = mustLoadEnvironmentVariable(SQUARE_APP_ID_ENV_VAR);
        squareLocationId = mustLoadEnvironmentVariable(SQUARE_LOCATION_ID_ENV_VAR);

        SquareClient squareClient = new SquareClient.Builder()
                .environment(Environment.fromString(squareEnvironment))
                .accessToken(mustLoadEnvironmentVariable(SQUARE_ACCESS_TOKEN_ENV_VAR))
                .build();

    }

       public ApiClient(PaymentsApi paymentsApi, String squareLocationId, String squareAppId, String squareEnvironment) throws IOException {

            this.squareLocationId = squareLocationId;
            this.squareAppId = squareAppId;
            this.squareEnvironment = squareEnvironment;
            this.paymentsApi = paymentsApi;
        }

    private String mustLoadEnvironmentVariable(String name) {
        String value = System.getenv(name);
        if (value == null || value.length() == 0) {
            throw new IllegalStateException(
                    String.format("The %s environment variable must be set", name));
        }

        return value;
    }


    public PaymentsApi getPaymentsApi() {
        return paymentsApi;
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

}
