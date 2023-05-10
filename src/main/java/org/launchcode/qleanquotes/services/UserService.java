package org.launchcode.qleanquotes.services;



import org.launchcode.qleanquotes.models.Customer;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    public void saveUser(Customer customer);
    public List<Object> isUserPresent(Customer customer);

}
