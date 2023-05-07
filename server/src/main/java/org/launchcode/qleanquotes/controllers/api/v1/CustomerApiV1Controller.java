package org.launchcode.qleanquotes.controllers.api.v1;

import org.launchcode.qleanquotes.models.Customer;
import org.launchcode.qleanquotes.models.data.CustomerRepository;
import org.launchcode.qleanquotes.models.dto.api.v1.CustomerApiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerApiV1Controller {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public ResponseEntity<CustomerApiDTO> getCustomer(JwtAuthenticationToken jwtToken) {
        Customer customer = customerRepository.findByEmail(jwtToken.getName());
        CustomerApiDTO response = new CustomerApiDTO(customer);
        return ResponseEntity.ok().body(response);
    }

}
