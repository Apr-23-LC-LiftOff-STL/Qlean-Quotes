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


    @PostMapping("/payment")
    public String createPaymentRequest (@ModelAttribute PaymentFormDTO paymentFormDTO, Model model) {

        try {

            // TODO: use money from `paymentFormDTO`
            Money amountMoney = new Money.Builder()
                .amount(1000L)
                .currency("USD")
                .build();

            // TODO: use `Address.Builder()` and the fields in `paymentFormDTO` to create an address object,
            //  do this for shipping and billing address
//          Address billingAddress = new Address.Builder()
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

            CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest
                    .Builder(paymentFormDTO.getToken(), paymentFormDTO.getIdempotencyKey())
                    .amountMoney(amountMoney)
                    // TODO: Use the Address objects created above and add them to the request
                    //.billingAddress(billingAddress)
                    //.shippingAddress(shippingAddress)
                    // TODO: Decide how to get customer id and order id from database for the transaction
                    //   these are not strictly necessary for a successful payment call to Square
                    //.customerId("W92WH6P11H4Z77CTET0RNTGFW8")
                    //.orderId("orderId")
                    .build();

            PaymentResult paymentResult = squareWrapper.createPayment(createPaymentRequest);
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
        return "payment-successful";
    }
}

//TODO: create a payment successful page and redirect users to it!