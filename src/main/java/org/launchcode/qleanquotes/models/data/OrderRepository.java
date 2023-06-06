package org.launchcode.qleanquotes.models.data;
import org.launchcode.qleanquotes.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
