package models.data;

import models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
@Component
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //the findByEmail method should be automatically generated by the JpaRepository.
    // But it's not, until we can figure that out, we'll leave this method here.
    Customer findByEmail(String email);
}