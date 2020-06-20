package com.nagp.orderservice.service;

import com.nagp.orderservice.service.impl.OrderServiceImpl;
import com.nagp.orderservice.dto.UserOrderDTO;

/**
 * Service interface to get order details of the user. The interface provides
 * one method to fetch list of order details (Order Id, Order Amount and Order
 * Date).
 * 
 * @author santoshkumar02
 * @see OrderServiceImpl
 */
public interface OrderService {

	/**
	 * Returns order details (Order Id, Order Amount and Order Date).
	 * 
	 * @param userId
	 * @return An object of UserOrderDTO containing order details
	 */
	UserOrderDTO getOrdersOfUser(Long userId);

}
