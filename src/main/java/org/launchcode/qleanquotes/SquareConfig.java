package org.launchcode.qleanquotes;

import com.squareup.square.Environment;
import com.squareup.square.api.CustomersApi;
import com.squareup.square.api.LocationsApi;
import com.squareup.square.api.OrdersApi;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.Location;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SquareConfig {
    @Bean
    public SquareClient squareClient(LocationsApi locationsApi, PaymentsApi paymentsApi, CustomersApi customersApi, OrdersApi ordersApi) {
        return new SquareClient(locationsApi, paymentsApi, customersApi, ordersApi);
    }

}
