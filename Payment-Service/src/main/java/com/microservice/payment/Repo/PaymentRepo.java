package com.microservice.payment.Repo;

import com.microservice.payment.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Integer> {


    Payment findByOrderId(int orderId);
}
