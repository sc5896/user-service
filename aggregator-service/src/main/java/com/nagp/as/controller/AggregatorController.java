package com.nagp.as.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.as.dto.OrderDetailsDTO;
import com.nagp.as.service.AggregatorService;

@RestController
public class AggregatorController {
	@Autowired
	private AggregatorService aggregatorService;

	@GetMapping(value = "/orderdetails/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDetailsDTO> getUser(@PathVariable Long userId) {
		OrderDetailsDTO orderDetails = aggregatorService.getUserOrderDetails(userId);
		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
	}
}
