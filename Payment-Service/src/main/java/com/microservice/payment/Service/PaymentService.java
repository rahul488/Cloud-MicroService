package com.microservice.payment.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.payment.Entity.Payment;
import com.microservice.payment.Repo.PaymentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;
    private Logger logger= LoggerFactory.getLogger(PaymentService.class);

    public Payment doPayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString() );
        logger.info("payment service request",new ObjectMapper().writeValueAsString(payment));
        return paymentRepo.save(payment);
    }

    public String paymentProcessing(){
        return new Random().nextBoolean()?"Success":"fail";
    }

    public Payment findPaymentHistoryByOrderId(int orderId) {

        return paymentRepo.findByOrderId(orderId);

    }
}
