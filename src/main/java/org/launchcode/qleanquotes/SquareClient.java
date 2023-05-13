package org.launchcode.qleanquotes;

import com.squareup.square.api.PaymentsApi;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.Money;

public class SquareClient {

    private PaymentsApi paymentsApi;

    public SquareClient(PaymentsApi paymentsApi) {
        this.paymentsApi = paymentsApi;
    }
        public void CreatePayment {
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
}
