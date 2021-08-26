package com.microservice.SpringCloud.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.SpringCloud.Common.Payment;
import com.microservice.SpringCloud.Common.TransactionRequest;
import com.microservice.SpringCloud.Common.TransactionResponse;
import com.microservice.SpringCloud.Entity.Order;
import com.microservice.SpringCloud.Repo.OrderRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    private Logger log= LoggerFactory.getLogger(OrderService.class);

    public TransactionResponse saveOrder(TransactionRequest transactionRequest) throws JsonProcessingException {

        String response="";

        Order order= transactionRequest.getOrder();
        Payment payment= transactionRequest.getPayment();

        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        log.info("OrderService request:{}",new ObjectMapper().writeValueAsString(transactionRequest));

        //rest call

       Payment paymentResponse=restTemplate.postForObject(ENDPOINT_URL,payment,Payment.class);
        log.info("PaymentService response:{}",new ObjectMapper().writeValueAsString(paymentResponse));
       response=paymentResponse.getPaymentStatus().equals("Success")?"payment processing successfull":"payment failed";

        orderRepo.save(order);
        return new TransactionResponse(order,paymentResponse.getTransactionId(),paymentResponse.getAmount(),response);
    }
}
