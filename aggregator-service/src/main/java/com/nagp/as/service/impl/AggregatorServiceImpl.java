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

/**
 * Service implementation of AggregatorService. Method getUserOrderDetails
 * internally calls user-service to get User details and order-service to fetch
 * order details.
 * 
 * @author santoshkumar02
 *
 */
@Service
public class AggregatorServiceImpl implements AggregatorService {
	/**
	 * Instance of rest template to make http request to other service.
	 */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * base url of user service
	 */
	@Value("${service.user-service.url}")
	private String userServiceUrl;

	/**
	 * base url of order service
	 */
	@Value("${service.order-service.url}")
	private String orderServiceUrl;

	/**
	 * Returns User Details (name, age and email) with the list of order details
	 * (Order Id, Order Amount and Order Date).
	 * 
	 * @param userId
	 * @return An object of OrderDetailsDTO containing User Details and the
	 *         order details
	 */
	@Override
	public OrderDetailsDTO getUserOrderDetails(Long userId) {
		OrderDetailsDTO OrderDetails = new OrderDetailsDTO();
		OrderDetails.setUserDetails(getUserDetails(userId));
		OrderDetails.setOrders(getUserOrders(userId));
		return OrderDetails;
	}

	/**
	 * Fetches user details by http request from user-service
	 * 
	 * @param userId
	 * @return user details
	 */
	private UserDTO getUserDetails(Long userId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> entity = new HttpEntity<>(null, headers);
		ResponseEntity<UserDTO> responseEntity = null;
		UserDTO userDetails = null;
		try {
			responseEntity = restTemplate.exchange(userServiceUrl + "/user/{id}", HttpMethod.GET, entity, UserDTO.class,
					userId);
			if ((HttpStatus.OK).equals(responseEntity.getStatusCode())) {
				userDetails = responseEntity.getBody();
			}
		} catch (Exception e) {
			throw new ASException(ASErrEnum.AS03, e);
		}
		if (userDetails == null) {
			throw new ASException(ASErrEnum.AS02);
		}
		return userDetails;
	}

	/**
	 * Fetches user details by http request from order-service
	 * 
	 * @param userId
	 * @return order details
	 */
	private List<OrderDTO> getUserOrders(Long userId) {
		List<OrderDTO> orders = null;
		ResponseEntity<UserOrderDTO> responseEntity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> entity = new HttpEntity<>(null, headers);

		try {
			responseEntity = restTemplate.exchange(orderServiceUrl + "/orders/{userId}", HttpMethod.GET, entity,
					UserOrderDTO.class, userId);
			if ((HttpStatus.OK).equals(responseEntity.getStatusCode())) {
				orders = responseEntity.getBody().getOrders();
			}
		} catch (Exception e) {
			throw new ASException(ASErrEnum.AS04, e);
		}

		return orders;
	}

}
