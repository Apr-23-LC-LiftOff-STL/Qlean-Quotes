package org.launchcode.qleanquotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.launchcode.qleanquotes.SquareClient;

@Service
public class PaymentService {
    private final SquareClient squareClient;

    @Autowired
    public PaymentService(SquareClient squareClient) {
        this.squareClient = squareClient;
    }

    public void makePayment() {
        // Call the createPayment() method on the injected SquareClient instance
        squareClient.createPayment();
    }
}
