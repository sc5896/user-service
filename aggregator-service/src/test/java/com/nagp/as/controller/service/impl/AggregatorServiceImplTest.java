package com.nagp.as.controller.service.impl;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.nagp.as.dto.OrderDetailsDTO;
import com.nagp.as.dto.UserDTO;
import com.nagp.as.dto.UserOrderDTO;
import com.nagp.as.service.impl.AggregatorServiceImpl;

public class AggregatorServiceImplTest {
	@InjectMocks
	private AggregatorServiceImpl aggregatorService;

	@Mock
	private RestTemplate restTemplate;

	@BeforeEach
	public void setup() {
		aggregatorService = new AggregatorServiceImpl();
		ReflectionTestUtils.setField(aggregatorService, "userServiceUrl", "http://user-service:8082");
		ReflectionTestUtils.setField(aggregatorService, "orderServiceUrl", "http://order-service:8082");
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Test AggregatorService getOrdersOfUser with user id 1")
	@Test
	public void getOrdersOfUser() {
		ResponseEntity<UserDTO> responseEntity1 = new ResponseEntity<>(new UserDTO(), HttpStatus.OK);
		UserOrderDTO userOrderDTO = new UserOrderDTO();
		userOrderDTO.setOrders(new ArrayList<>());
		ResponseEntity<UserOrderDTO> responseEntity2 = new ResponseEntity<>(userOrderDTO, HttpStatus.OK);

		Mockito.when(
				restTemplate.exchange(ArgumentMatchers.eq("http://user-service:8082/user/{id}"), ArgumentMatchers.any(),
						ArgumentMatchers.any(), ArgumentMatchers.<Class<UserDTO>>any(), ArgumentMatchers.<Long>any()))
				.thenReturn(responseEntity1);
		Mockito.when(restTemplate.exchange(Mockito.eq("http://order-service:8082/orders/{userId}"),
				ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.<Class<UserOrderDTO>>any(),
				ArgumentMatchers.<Long>any())).thenReturn(responseEntity2);
		OrderDetailsDTO orderDetails = aggregatorService.getUserOrderDetails(1L);
		Assertions.assertNotNull(orderDetails);
		Assertions.assertNotNull(orderDetails.getUserDetails());
		Assertions.assertNotNull(orderDetails.getOrders());
	}
}
