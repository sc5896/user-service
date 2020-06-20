package com.nagp.orderservice.dto;

import java.util.List;

import lombok.Data;

/**
 * Data transfer object list of order details
 * 
 * @author santoshkumar02
 *
 */
@Data
public class UserOrderDTO {
	private List<OrderDTO> orders;
}
