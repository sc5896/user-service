package com.nagp.userservice.controller;

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

import com.nagp.userservice.dto.UserDTO;
import com.nagp.userservice.service.UserService;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;

public class UserControllerTest {
	
    private MockMvc mockMvc;
	
    @Mock
	private Tracer tracer;
    
    @Mock
	private SpanBuilder spanBuilder;
	
	@Mock
	private Span span;
    
	@Mock
	private UserService userService;
	
    @InjectMocks
    private UserController userController;
	
	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
	
	@DisplayName("Test UserService getUserById with user id 1")
	@Test
	public void getUserById() throws Exception {
		Mockito.when(userService.getUserById(Mockito.anyLong())).thenReturn(getUserDto());
		Mockito.when(tracer.buildSpan(Mockito.anyString())).thenReturn(spanBuilder);
		Mockito.when(spanBuilder.start()).thenReturn(span);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}",1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
		String resultSS = result.getResponse().getContentAsString();
		Assertions.assertNotNull(resultSS);
	}
	
	private UserDTO getUserDto(){
		UserDTO user = new UserDTO();
		user.setId(1L);
		user.setName("John");
		user.setAge(23);
		user.setEmail("john.doe@google.com");
		return user;
	}
}
