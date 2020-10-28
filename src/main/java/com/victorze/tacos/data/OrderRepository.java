package com.victorze.tacos.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.victorze.tacos.Order;
import com.victorze.tacos.User;

public interface OrderRepository extends CrudRepository<Order, Long> {
	
	List<Order> findByDeliveryZip(String deliveryZip);
	
	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
	
}