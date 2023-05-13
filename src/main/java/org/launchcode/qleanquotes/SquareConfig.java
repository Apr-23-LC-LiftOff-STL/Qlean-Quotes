package org.launchcode.qleanquotes;

import com.squareup.square.api.PaymentsApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SquareConfig {
    @Bean
    public SquareClient squareClient(PaymentsApi paymentsApi) {
        return new SquareClient(paymentsApi);
    }
}
