package com.nagp.as.controller;

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

import com.nagp.as.dto.OrderDetailsDTO;
import com.nagp.as.service.AggregatorService;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;

public class AggregatorControllerTest {
	private MockMvc mockMvc;

	@Mock
	private AggregatorService aggregatorService;

	@Mock
	private SpanBuilder spanBuilder;

	@Mock
	private Tracer tracer;

	@Mock
	private Span span;

	@InjectMocks
	private AggregatorController aggregatorController;
	
	private OrderDetailsDTO orderDetails; 

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(aggregatorController).build();
		orderDetails = new OrderDetailsDTO();
	}

	@DisplayName("Test AggregaotrController getUserOrderDetails with user id 1")
	@Test
	public void getUserOrderDetails() throws Exception {
		Mockito.when(aggregatorService.getUserOrderDetails(Mockito.anyLong())).thenReturn(orderDetails);
		Mockito.when(tracer.buildSpan(Mockito.anyString())).thenReturn(spanBuilder);
		Mockito.when(spanBuilder.start()).thenReturn(span);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/orderdetails/{userId}", 1L).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String resultSS = result.getResponse().getContentAsString();
		Assertions.assertNotNull(resultSS);
	}
}
