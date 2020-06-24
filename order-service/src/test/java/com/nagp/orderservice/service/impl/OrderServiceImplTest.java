package com.nagp.orderservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.nagp.orderservice.dto.UserOrderDTO;

@SpringBootTest
public class OrderServiceImplTest {
	
	@InjectMocks
	private OrderServiceImpl orderService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@DisplayName("Test OrderService getOrdersOfUser with user id 1")
	@Test
	public void getOrdersOfUser() {
		UserOrderDTO userOrder = orderService.getOrdersOfUser(1L);
		Assertions.assertNotNull(userOrder);
		Assertions.assertNotNull(userOrder.getOrders());
		Assertions.assertEquals(2,userOrder.getOrders().size());
	}
}
