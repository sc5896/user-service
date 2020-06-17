package com.nagp.orderservice.service;

import com.nagp.orderservice.dto.UserOrderDTO;

public interface OrderService {

	UserOrderDTO getOrdersOfUser(Long userId);

}
