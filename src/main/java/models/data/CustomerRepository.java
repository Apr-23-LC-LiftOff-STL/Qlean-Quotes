package models.data;

import models.Customer;
import models.dto.RegisterFormDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    Customer findByEmail(String email);
}