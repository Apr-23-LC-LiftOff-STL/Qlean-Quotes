package org.launchcode.qleanquotes.models.data;

import org.launchcode.qleanquotes.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    Customer findByEmail(String email);
}

