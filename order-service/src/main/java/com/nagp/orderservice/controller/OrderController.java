package com.nagp.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.orderservice.dto.UserOrderDTO;
import com.nagp.orderservice.service.OrderService;

import io.opentracing.Span;
import io.opentracing.Tracer;

/**
 * This controller class contains rest end points to get order details.
 * 
 * @author santoshkumar02
 *
 */
@RestController
public class OrderController {
	
	@Autowired
	private Tracer tracer;

	@Autowired
	private OrderService orderService;

	/**
	 * This method returns order details of the given user.
	 * 
	 * @param userId
	 * @return order details of the given user id
	 */
	@GetMapping(value = "/orders/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserOrderDTO> getOrdersOfUser(@PathVariable Long userId) {
		Span span = tracer.buildSpan("order-service").start();
		UserOrderDTO userOrderDTO = orderService.getOrdersOfUser(userId);
		span.finish();
		return new ResponseEntity<>(userOrderDTO, HttpStatus.OK);
	}
	
}
