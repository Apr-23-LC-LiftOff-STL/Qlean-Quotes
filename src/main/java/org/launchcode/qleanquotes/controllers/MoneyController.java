package org.launchcode.qleanquotes.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.squareup.square.ApiHelper;
import com.squareup.square.Environment;
import com.squareup.square.Server;
import com.squareup.square.api.BaseApi;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.http.client.HttpContext;
import com.squareup.square.http.request.HttpMethod;
import com.squareup.square.models.*;
import io.apimatic.core.ApiCall;
import org.launchcode.qleanquotes.SquareClient;
import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.PaymentResult;
import org.launchcode.qleanquotes.models.TokenWrapper;
import org.launchcode.qleanquotes.models.dto.CreateQuoteFormDTO;
import org.launchcode.qleanquotes.models.dto.RegisterFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.launchcode.qleanquotes.models.dto.PaymentFormDTO;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;


@Controller
public class MoneyController {

    @GetMapping("/payment")
    public String showPaymentForm(Model model){
        model.addAttribute(new PaymentFormDTO());
        return "payment";
    }

    @RequestMapping("/payment")
    String index(Map<String, Object> model, @ModelAttribute SquareClient squareClient) throws InterruptedException, ExecutionException {

        model.put("paymentFormUrl",
                squareClient.getSquareEnvironment().equals("sandbox") ? "https://sandbox.web.squarecdn.com/v2/payments"
                        : "https://web.squarecdn.com/v2/payments");
        model.put("locationId", squareClient.getSquareLocationId());
        model.put("appId", squareClient.getSquareAppId());
        model.put("idempotencyKey", UUID.randomUUID().toString());

        return "payment";
    }

    @PostMapping("/process-payment")
    @ResponseBody
    PaymentResult processPayment(@RequestBody TokenWrapper tokenObject, @ModelAttribute PaymentFormDTO paymentFormDTO, @ModelAttribute SquareClient squareClient, @ModelAttribute RegisterFormDTO customer, Model model)
            throws InterruptedException, ExecutionException {

        Money amountMoney = new Money.Builder()
                .amount(1000L)
                .currency("USD")
                .build();

        Address billingAddress = new Address.Builder()
                .addressLine1(paymentFormDTO.getBillingAddressLine1())
                .addressLine2(paymentFormDTO.getBillingAddressLine2())
                .locality(paymentFormDTO.getBillingLocality())
                .administrativeDistrictLevel1(paymentFormDTO.getBillingAdministrativeDistrictLevel1())
                .postalCode(paymentFormDTO.getBillingPostalCode())
                .build();

        Address shippingAddress = new Address.Builder()
                .addressLine1(paymentFormDTO.getShippingAddressLine1())
                .addressLine2(paymentFormDTO.getShippingAddressLine2())
                .locality(paymentFormDTO.getShippingLocality())
                .administrativeDistrictLevel1(paymentFormDTO.getShippingAdministrativeDistrictLevel1())
                .postalCode(paymentFormDTO.getShippingPostalCode())
                .build();

        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest.Builder(
                tokenObject.getToken(),
                tokenObject.getIdempotencyKey())
                .amountMoney(amountMoney)
                .autocomplete(true)
                .buyerEmailAddress(customer.getEmail())
                .billingAddress(billingAddress)
                .shippingAddress(shippingAddress)
                // .customerId("W92WH6P11H4Z77CTET0RNTGFW8")
                //.orderId("orderId")
                .build();

        PaymentsApi paymentsApi = squareClient.getPaymentsApi();

        return paymentsApi.createPaymentAsync(createPaymentRequest).thenApply(result -> {
            return new PaymentResult("SUCCESS", null);
        }).exceptionally(exception -> {
            ApiException e = (ApiException) exception.getCause();
            System.out.println("Failed to make the request");
            System.out.printf("Exception: %s%n", e.getMessage());
            return new PaymentResult("FAILURE", e.getErrors());
        }).join();
    }

}

