package com.nagp.as.dto;

import java.util.List;

import lombok.Data;

/**
 * Data transfer object for user and its order details
 * 
 * @author santoshkumar02
 *
 */
@Data
public class OrderDetailsDTO {
	private UserDTO userDetails;
	private List<OrderDTO> orders;
}
