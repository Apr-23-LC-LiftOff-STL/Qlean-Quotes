package org.launchcode.qleanquotes;

import com.squareup.square.Environment;
import com.squareup.square.api.CustomersApi;
import com.squareup.square.api.LocationsApi;
import com.squareup.square.api.OrdersApi;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.Error;
import java.util.LinkedList;
import java.util.Properties;

public class SquareClient {

    private LocationsApi locationsApi;
    private PaymentsApi paymentsApi;
    private CustomersApi customersApi;
    private OrdersApi ordersApi;


    public SquareClient(LocationsApi locationsApi, PaymentsApi paymentsApi, CustomersApi customersApi, OrdersApi ordersApi) throws IOException {

        this.locationsApi = locationsApi;
        this.paymentsApi = paymentsApi;
        this.customersApi = customersApi;
        this.ordersApi = ordersApi;
    }

    // TODO research how to configure SquareClient using the Quickstart code
    //
    // InputStream inputStream =
//            SquareClient.class.getResourceAsStream("/config.properties");
//    Properties prop = new Properties();
//
//            try {
//        prop.load(inputStream);
//    } catch (
//    IOException e) {
//        System.out.println("Error reading properties file");
//        e.printStackTrace();
//    }
//
//    SquareClient client = new SquareClient.Builder()
//            .accessToken(prop.getProperty("SQUARE_ACCESS_TOKEN"))
//            .environment(Environment.SANDBOX)
//            .build();
//
//            locationsApi.listLocationsAsync().thenAccept(result -> {
//        System.out.println("Location(s) for this account:");
//
//        for (Location l : result.getLocations()) {
//            System.out.printf("%s: %s, %s, %s\n",
//                    l.getId(), l.getName(),
//                    l.getAddress().getAddressLine1(),
//                    l.getAddress().getLocality());
//        }
//
//    }).exceptionally(exception -> {
//        try {
//            throw exception.getCause();
//        } catch (ApiException ae) {
//            for (Error err : ae.getErrors()) {
//                System.out.println(err.getCategory());
//                System.out.println(err.getCode());
//                System.out.println(err.getDetail());
//            }
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//        return null;
//    }).join();
//
//            SquareClient.shutdown();

    //TODO align API requirements and desired functionality with data to be collected client-side
    //TODO replace hard-coded strings with Money variables
        public void CreatePayment (){
            Money amountMoney = new Money.Builder()
                    .amount(1000L)
                    .currency("USD")
                    .build();

            Money appFeeMoney = new Money.Builder()
                    .amount(10L)
                    .currency("USD")
                    .build();

            CreatePaymentRequest request = new CreatePaymentRequest.Builder("ccof:GaJGNaZa8x4OgDJn4GB", "7b0f3ec5-086a-4871-8f13-3c81b3875218")
                    .amountMoney(amountMoney)
                    .appFeeMoney(appFeeMoney)
                    .autocomplete(true)
                    .customerId("W92WH6P11H4Z77CTET0RNTGFW8")
                    .locationId("L88917AVBK2S5")
                    .referenceId("123456")
                    .note("Brief description")
                    .build();

            paymentsApi.createPaymentAsync(request)
                    .thenAccept(result -> {
                        System.out.println("Success!");
                    })
                    .exceptionally(exception -> {
                        System.out.println("Failed to make the request");
                        System.out.println(String.format("Exception: %s", exception.getMessage()));
                        return null;
                    });
        }
    //TODO align API requirements and desired functionality with data to be collected client-side
    //TODO replace hard-coded strings with Customer variables
        public void CreateCustomer() {
        CreateCustomerRequest request = new CreateCustomerRequest.Builder()
                .idempotencyKey("{UNIQUE_KEY}")
                .givenName("John")
                .familyName("Doe")
                .build();

            customersApi.createCustomerAsync(request)
                .thenAccept(result -> {
                    System.out.println("Success!");
                })
                .exceptionally(exception -> {
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", exception.getMessage()));
                    return null;
                });
    }

    //TODO align API requirements and desired functionality with data to be collected client-side
    //TODO replace hard-coded strings with Customer variables
    public void CreateOrder (){
        OrderLineItemModifier orderLineItemModifier = new OrderLineItemModifier.Builder()
                .catalogObjectId("{MODIFIER_ID}")
                .quantity("1")
                .build();

        LinkedList<OrderLineItemModifier> modifiers = new LinkedList<>();
        modifiers.add(orderLineItemModifier);

        OrderLineItem orderLineItem = new OrderLineItem.Builder("1")
                .catalogObjectId("{ITEM_VARIATION_ID}")
                .modifiers(modifiers)
                .build();

        LinkedList<OrderLineItem> lineItems = new LinkedList<>();
        lineItems.add(orderLineItem);

        Order order = new Order.Builder("{LOCATION_ID}")
                .lineItems(lineItems)
                .build();

        CreateOrderRequest request = new CreateOrderRequest.Builder()
                .order(order)
                .idempotencyKey("{UNIQUE_KEY}")
                .build();

        ordersApi.createOrderAsync(request)
                .thenAccept(result -> {
                    System.out.println("Success!");
                })
                .exceptionally(exception -> {
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", exception.getMessage()));
                    return null;
                });
    }
}
