package com.nagp.as.service;

import com.nagp.as.service.impl.AggregatorServiceImpl;
import com.nagp.as.dto.OrderDetailsDTO;

/**
 * Service interface to get user details and order details of the user. The
 * interface provides one method to fetch user details (name, age and email) and
 * list of order details (Order Id, Order Amount and Order Date).
 * 
 * @author santoshkumar02
 * @see AggregatorServiceImpl
 */
public interface AggregatorService {
	/**
	 * Returns User Details (name, age and email) with the list of order details
	 * (Order Id, Order Amount and Order Date).
	 * 
	 * @param userId
	 * @return An object of OrderDetailsDTO containing User Details and the
	 *         order details
	 */
	OrderDetailsDTO getUserOrderDetails(Long userId);
}
