package org.launchcode.qleanquotes.controllers;

import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.CreatePaymentResponse;
import com.squareup.square.models.Money;
import org.launchcode.qleanquotes.SquareWrapper;
import org.launchcode.qleanquotes.models.PaymentResult;
import org.launchcode.qleanquotes.models.TokenWrapper;
import org.launchcode.qleanquotes.models.dto.PaymentFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

    @Controller
    public class NewMoneyController {
        private final SquareWrapper squareWrapper;

        @Autowired
        public NewMoneyController(SquareWrapper squareWrapper) {
            this.squareWrapper = squareWrapper;
        }

        @GetMapping("/payment")
        public String showPaymentForm(Model model){
            model.addAttribute(new PaymentFormDTO());
            return "payment";
        }

        @RequestMapping("")
        public String index(Map<String, Object> model) throws InterruptedException, ExecutionException {
        model.put("paymentFormUrl",
                squareWrapper.getSquareEnvironment());
        model.put("locationId", squareWrapper.getSquareLocationId());
        model.put("appId", squareWrapper.getSquareAppId());
        model.put("idempotencyKey", UUID.randomUUID().toString());

        return "payment";
    }

//     TODO: get body in here
    @PostMapping("/payment")
    public String createPaymentRequest (@ModelAttribute PaymentFormDTO paymentFormDTO, TokenWrapper token, Model model) {

        try {

            Money amountMoney = new Money.Builder()
                .amount(1000L)
                .currency("USD")
                .build();

            CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest.Builder(
                token.getToken(),
                token.getIdempotencyKey())
                .amountMoney(amountMoney)
                .autocomplete(true)
//                .billingAddress(billingAddress)
//                .shippingAddress(shippingAddress)
                // .customerId("W92WH6P11H4Z77CTET0RNTGFW8")
                //.orderId("orderId")
                .build();

            PaymentResult paymentResult = squareWrapper.createPayment(createPaymentRequest, paymentFormDTO, token);
            System.out.println(paymentResult.getTitle());
            model.addAttribute("paymentResult", paymentResult);
            if (paymentResult.getTitle().equals("SUCCESS")) {
                System.out.println("Payment Successful");
            } else {
                System.out.println("Payment Failed");
                // Handle the failure scenario, such as displaying an error message to the user or taking corrective actions
            }
            // Process the payment result
        } catch (ApiException | IOException e) {
            // Handle exceptions
            System.out.println(e);
        }
        return "payment";
    }
}
