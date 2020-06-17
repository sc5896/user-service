package com.nagp.as.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagp.as.dto.OrderDTO;
import com.nagp.as.dto.OrderDetailsDTO;
import com.nagp.as.dto.UserDTO;
import com.nagp.as.dto.UserOrderDTO;
import com.nagp.as.exception.ASErrEnum;
import com.nagp.as.exception.ASException;
import com.nagp.as.service.AggregatorService;

@Service
public class AggregatorServiceImpl implements AggregatorService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${service.user-service.url}")
	private String userServiceUrl;
	
	@Value("${service.order-service.url}")
	private String orderServiceUrl;
	
	@Override
	public OrderDetailsDTO getUserOrderDetails(Long userId) {
		OrderDetailsDTO OrderDetails = new OrderDetailsDTO();
		OrderDetails.setUserDetails(getUserDetails(userId));
		OrderDetails.setOrders(getUserOrders(userId));
		return OrderDetails;
	}
	
	private UserDTO getUserDetails(Long userId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> entity = new HttpEntity<>(null, headers);
		ResponseEntity<UserDTO> responseEntity = null;
		UserDTO userDetails = null;
		try {
			responseEntity = restTemplate.exchange(userServiceUrl+"/user/{id}", HttpMethod.GET, entity,
					UserDTO.class, userId);
			if ((HttpStatus.OK).equals(responseEntity.getStatusCode())) {
				userDetails = responseEntity.getBody();
			}
		} catch(Exception e) {
			throw new ASException(ASErrEnum.AS03,e);
		}
		if(userDetails==null) {
			throw new ASException(ASErrEnum.AS02);
		}
		return userDetails;
	}
	
	private List<OrderDTO> getUserOrders(Long userId) {
		List<OrderDTO> orders= null;
		ResponseEntity<UserOrderDTO> responseEntity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> entity = new HttpEntity<>(null, headers);

		try {
			responseEntity = restTemplate.exchange(orderServiceUrl+"/orders/{userId}", HttpMethod.GET, entity,
					UserOrderDTO.class, userId);
			if ((HttpStatus.OK).equals(responseEntity.getStatusCode())) {
				orders = responseEntity.getBody().getOrders();
			}
		} catch(Exception e) {
			throw new ASException(ASErrEnum.AS03,e);
		}
		
		return orders;
	}
	
}
