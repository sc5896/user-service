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

import io.opentracing.Span;
import io.opentracing.Tracer;

/**
 * This controller class contains rest end points to get user and order details.
 * 
 * @author santoshkumar02
 *
 */
@RestController
public class AggregatorController {
	@Autowired
	private Tracer tracer;
	
	@Autowired
	private AggregatorService aggregatorService;

	/**
	 * Returns the user details along with the list of order details.
	 * 
	 * @param userId
	 *            - User Id
	 * @return - user details and its order details
	 */
	@GetMapping(value = "/orderdetails/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDetailsDTO> getUser(@PathVariable Long userId) {
		Span span = tracer.buildSpan("aggregator-service").start();
		OrderDetailsDTO orderDetails = aggregatorService.getUserOrderDetails(userId);
		span.setTag("http.status_code", 200);
		span.finish();
		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
	}
}
