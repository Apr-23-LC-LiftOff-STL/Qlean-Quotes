package org.launchcode.qleanquotes.controllers;

import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.CreatePaymentRequest;
import org.launchcode.qleanquotes.SquareWrapper;
import org.launchcode.qleanquotes.models.PaymentResult;
import org.launchcode.qleanquotes.models.TokenWrapper;
import org.launchcode.qleanquotes.models.dto.PaymentFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

    @RestController
    @RequestMapping("/api")
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

        return "index";
    }

    @PostMapping("/payment")
    public String createPaymentRequest (@ModelAttribute PaymentFormDTO paymentFormDTO, @ModelAttribute CreatePaymentRequest body, @ModelAttribute TokenWrapper tokenObject, Model model) {

        try {
            PaymentResult paymentResult = squareWrapper.createPayment(body, paymentFormDTO, tokenObject);
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
        }
        return "payment";
    }
}
