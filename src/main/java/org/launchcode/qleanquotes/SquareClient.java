package org.launchcode.qleanquotes;

import com.squareup.square.Environment;
import com.squareup.square.api.CustomersApi;
import com.squareup.square.api.LocationsApi;
import com.squareup.square.api.OrdersApi;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;

import java.io.IOException;

public class SquareClient {

    private LocationsApi locationsApi;
    private PaymentsApi paymentsApi;
    private CustomersApi customersApi;
    private OrdersApi ordersApi;


    public SquareClient(LocationsApi locationsApi, PaymentsApi paymentsApi, CustomersApi customersApi, OrdersApi ordersApi, com.squareup.square.SquareClient squareClient, String squareLocationId, String squareAppId, String squareEnvironment) throws IOException {

        this.squareClient = squareClient;
        this.squareLocationId = squareLocationId;
        this.squareAppId = squareAppId;
        this.squareEnvironment = squareEnvironment;
        this.locationsApi = locationsApi;
        this.paymentsApi = paymentsApi;
        this.customersApi = customersApi;
        this.ordersApi = ordersApi;
    }

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

    private final com.squareup.square.SquareClient squareClient;
    private final String squareLocationId;
    private final String squareAppId;
    private final String squareEnvironment;

    public SquareClient() throws ApiException {
        squareEnvironment = mustLoadEnvironmentVariable(SQUARE_ENV_ENV_VAR);
        squareAppId = mustLoadEnvironmentVariable(SQUARE_APP_ID_ENV_VAR);
        squareLocationId = mustLoadEnvironmentVariable(SQUARE_LOCATION_ID_ENV_VAR);

        squareClient = new com.squareup.square.SquareClient.Builder()
                .environment(Environment.fromString(squareEnvironment))
                .accessToken(mustLoadEnvironmentVariable(SQUARE_ACCESS_TOKEN_ENV_VAR))
                .build();

    }

    private String mustLoadEnvironmentVariable(String name) {
        String value = System.getenv(name);
        if (value == null || value.length() == 0) {
            throw new IllegalStateException(
                    String.format("The %s environment variable must be set", name));
        }

        return value;
    }

    public LocationsApi getLocationsApi() {
        return locationsApi;
    }

    public PaymentsApi getPaymentsApi() {
        return paymentsApi;
    }

    public CustomersApi getCustomersApi() {
        return customersApi;
    }

    public OrdersApi getOrdersApi() {
        return ordersApi;
    }

    public com.squareup.square.SquareClient getSquareClient() {
        return squareClient;
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
