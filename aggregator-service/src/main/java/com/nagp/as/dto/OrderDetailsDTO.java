package com.nagp.as.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDetailsDTO {
	private UserDTO userDetails;
	private List<OrderDTO> orders;
}
