package com.nagp.orderservice.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nagp.orderservice.dto.OrderDTO;
import com.nagp.orderservice.dto.UserOrderDTO;
import com.nagp.orderservice.service.OrderService;
import com.nagp.orderservice.util.DateUtil;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;

public class OrderControllerTest {
	private static final String DT_FORMAT = "dd-MMM-yyyy";
	private static final String UTC_TZ = "UTC";
	
	private MockMvc mockMvc;

	@Mock
	private OrderService orderService;
	
	@Mock
	private SpanBuilder spanBuilder;

	@Mock
	private Tracer tracer;
	
	@Mock
	private Span span;

	@InjectMocks
	private OrderController orderController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}

	@DisplayName("Test OrderController getOrdersOfUser with user id 1")
	@Test
	public void getOrdersOfUser() throws Exception {
		Mockito.when(orderService.getOrdersOfUser(Mockito.anyLong())).thenReturn(getUserOrderDTO());
		Mockito.when(tracer.buildSpan(Mockito.anyString())).thenReturn(spanBuilder);
		Mockito.when(spanBuilder.start()).thenReturn(span);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/orders/{userId}", 1L).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String resultSS = result.getResponse().getContentAsString();
		Assertions.assertNotNull(resultSS);
	}

	private UserOrderDTO getUserOrderDTO() throws ParseException {
		UserOrderDTO userOrder = new UserOrderDTO();
		List<OrderDTO> orders = new ArrayList<>();
		OrderDTO order = new OrderDTO();
		order.setOrderId(1L);
		order.setOrderAmount(250);

		order.setOrderDate(DateUtil.getDate("14-Apr-2020", DT_FORMAT, TimeZone.getTimeZone(UTC_TZ)));
		orders.add(order);
		order = new OrderDTO();
		order.setOrderId(2L);
		order.setOrderAmount(450);
		order.setOrderDate(DateUtil.getDate("15-Apr-2020", DT_FORMAT, TimeZone.getTimeZone(UTC_TZ)));
		orders.add(order);
		userOrder.setOrders(orders);
		return userOrder;
	}
}
