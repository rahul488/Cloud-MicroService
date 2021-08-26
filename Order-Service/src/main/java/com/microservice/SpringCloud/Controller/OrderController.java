package com.microservice.SpringCloud.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.SpringCloud.Common.Payment;
import com.microservice.SpringCloud.Common.TransactionRequest;
import com.microservice.SpringCloud.Common.TransactionResponse;
import com.microservice.SpringCloud.Entity.Order;
import com.microservice.SpringCloud.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) throws JsonProcessingException {
        return  orderService.saveOrder(transactionRequest);
    }

}
