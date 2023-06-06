package org.launchcode.qleanquotes.models.data;
import org.launchcode.qleanquotes.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}
