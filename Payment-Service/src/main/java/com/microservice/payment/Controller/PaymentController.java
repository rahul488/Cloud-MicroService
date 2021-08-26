package com.microservice.payment.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.payment.Entity.Payment;
import com.microservice.payment.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public Payment pay(@RequestBody Payment payment) throws JsonProcessingException {
        return  paymentService.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable int orderId){

        return paymentService.findPaymentHistoryByOrderId(orderId);

    }

}
