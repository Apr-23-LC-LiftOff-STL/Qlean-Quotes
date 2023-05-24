package org.launchcode.qleanquotes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.squareup.square.ApiHelper;
import com.squareup.square.Environment;
import com.squareup.square.Server;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.http.client.HttpContext;
import com.squareup.square.http.request.HttpMethod;
import com.squareup.square.models.*;
import io.apimatic.core.ApiCall;
import org.launchcode.qleanquotes.models.PaymentResult;
import org.launchcode.qleanquotes.models.TokenWrapper;
import org.springframework.stereotype.Component;
import org.launchcode.qleanquotes.models.dto.PaymentFormDTO;

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
            final CreatePaymentRequest body, PaymentFormDTO paymentFormDTO, TokenWrapper tokenObject) throws ApiException, IOException {

//        Money amountMoney = new Money.Builder()
//                .amount(1000L)
//                .currency("USD")
//                .build();
//
//        Address billingAddress = new Address.Builder()
//                .addressLine1(paymentFormDTO.getBillingAddressLine1())
//                .addressLine2(paymentFormDTO.getBillingAddressLine2())
//                .locality(paymentFormDTO.getBillingLocality())
//                .administrativeDistrictLevel1(paymentFormDTO.getBillingAdministrativeDistrictLevel1())
//                .postalCode(paymentFormDTO.getBillingPostalCode())
//                .build();
//
//        Address shippingAddress = new Address.Builder()
//                .addressLine1(paymentFormDTO.getShippingAddressLine1())
//                .addressLine2(paymentFormDTO.getShippingAddressLine2())
//                .locality(paymentFormDTO.getShippingLocality())
//                .administrativeDistrictLevel1(paymentFormDTO.getShippingAdministrativeDistrictLevel1())
//                .postalCode(paymentFormDTO.getShippingPostalCode())
//                .build();
//
//        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest.Builder(
//                tokenObject.getToken(),
//                tokenObject.getIdempotencyKey())
//                .amountMoney(amountMoney)
//                .autocomplete(true)
//                .billingAddress(billingAddress)
//                .shippingAddress(shippingAddress)
//                // .customerId("W92WH6P11H4Z77CTET0RNTGFW8")
//                //.orderId("orderId")
//                .build();

        PaymentsApi paymentsApi = squareClient.getPaymentsApi();
        paymentsApi.createPayment(body);
        return new PaymentResult("SUCCESS", null);
    }

    private ApiCall<CreatePaymentResponse, ApiException>
        prepareCreatePaymentRequest(final CreatePaymentRequest body)
            throws JsonProcessingException, IOException {
        return new ApiCall.Builder<CreatePaymentResponse, ApiException>()
                .requestBuilder(requestBuilder -> requestBuilder
                        .path("https://connect.squareupsandbox.com/v2/payments")
                        .bodyParam(param -> param.value(body))
                        .bodySerializer(() ->  ApiHelper.serialize(body))
                        .headerParam(param -> param.key("Content-Type")
                                .value("application/json").isRequired(false))
                        .headerParam(param -> param.key("accept").value("application/json"))
                        .authenticationKey(mustLoadEnvironmentVariable(SQUARE_ACCESS_TOKEN_ENV_VAR))
                        .httpMethod(HttpMethod.POST))
                .responseHandler(responseHandler -> responseHandler
                        .deserializer(
                                response -> ApiHelper.deserialize(response, CreatePaymentResponse.class))
                        .nullify404(false)
                        .contextInitializer((context, result) ->
                                result.toBuilder().httpContext((HttpContext)context).build()))
                .build();
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
