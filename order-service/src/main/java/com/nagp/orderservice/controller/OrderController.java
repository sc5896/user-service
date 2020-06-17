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

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/orders/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserOrderDTO> getOrdersOfUser(@PathVariable Long userId) {
		return new ResponseEntity<>(orderService.getOrdersOfUser(userId), HttpStatus.OK);
	}
}
