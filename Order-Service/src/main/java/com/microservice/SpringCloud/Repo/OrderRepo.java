package com.microservice.SpringCloud.Repo;

import com.microservice.SpringCloud.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Integer> {
}
