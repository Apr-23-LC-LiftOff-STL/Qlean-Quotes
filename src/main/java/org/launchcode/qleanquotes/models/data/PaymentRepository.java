package org.launchcode.qleanquotes.models.data;

import org.launchcode.qleanquotes.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
