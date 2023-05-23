package org.launchcode.qleanquotes.controllers;

import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.*;
import jakarta.validation.Valid;
import com.squareup.square.SquareClient;
import org.launchcode.qleanquotes.ApiClient;
import org.launchcode.qleanquotes.models.PaymentResult;
import org.launchcode.qleanquotes.models.TokenWrapper;
import org.launchcode.qleanquotes.models.dto.RegisterFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.launchcode.qleanquotes.models.dto.PaymentFormDTO;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


@Controller
public class MoneyController {

    @GetMapping("/payment")
    public String showPaymentForm(Model model){
        model.addAttribute(new PaymentFormDTO());
        return "payment";
    }

    @RequestMapping("/")
    String index(Map<String, Object> model, @ModelAttribute ApiClient client) throws InterruptedException, ExecutionException {

        model.put("paymentFormUrl", client.getSquareEnvironment());
        model.put("locationId", client.getSquareLocationId());
        model.put("appId", client.getSquareAppId());
        model.put("idempotencyKey", UUID.randomUUID().toString());

        return "index";
    }

    @PostMapping("/process-payment")
    @ResponseBody
    PaymentResult processPayment(@RequestBody TokenWrapper tokenObject, @ModelAttribute @Valid PaymentFormDTO paymentForm, @ModelAttribute SquareClient squareClient, @ModelAttribute RegisterFormDTO customer, Model model)
            throws InterruptedException, ExecutionException {

        System.out.println("Top of request");

        Money amountMoney = new Money.Builder()
                .amount(1000L)
                .currency("USD")
                .build();

        Address billingAddress = new Address.Builder()
                .addressLine1(paymentForm.getBillingAddressLine1())
                .addressLine2(paymentForm.getBillingAddressLine2())
                .locality(paymentForm.getBillingLocality())
                .administrativeDistrictLevel1(paymentForm.getBillingAdministrativeDistrictLevel1())
                .postalCode(paymentForm.getBillingPostalCode())
                .build();

        Address shippingAddress = new Address.Builder()
                .addressLine1(paymentForm.getShippingAddressLine1())
                .addressLine2(paymentForm.getShippingAddressLine2())
                .locality(paymentForm.getShippingLocality())
                .administrativeDistrictLevel1(paymentForm.getShippingAdministrativeDistrictLevel1())
                .postalCode(paymentForm.getShippingPostalCode())
                .build();

        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest.Builder(
                tokenObject.getToken(),
                tokenObject.getIdempotencyKey())
                .amountMoney(amountMoney)
                .autocomplete(true)
                .buyerEmailAddress(customer.getEmail())
                .billingAddress(billingAddress)
                .shippingAddress(shippingAddress)
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

