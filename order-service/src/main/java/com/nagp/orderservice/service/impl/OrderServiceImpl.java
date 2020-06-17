package com.nagp.orderservice.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.nagp.orderservice.dto.OrderDTO;
import com.nagp.orderservice.dto.UserOrderDTO;
import com.nagp.orderservice.service.OrderService;
import com.nagp.orderservice.util.DateUtil;

@Service
public class OrderServiceImpl implements OrderService{
	private static final String DT_FORMAT = "dd-MMM-yyyy";
	private static final String UTC_TZ = "UTC";
	
	@Override
	public UserOrderDTO getOrdersOfUser(Long userId) {
		UserOrderDTO userOrders = new UserOrderDTO();
		try {
			userOrders.setOrders(generateDummyOrderList());
		} catch (ParseException e) {
			throw new RuntimeException("Date parsing exception", e);
		}
		return userOrders;
	}
	
	private List<OrderDTO> generateDummyOrderList() throws ParseException {
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
		return orders;
	}
	
}
