package com.nagp.as.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserOrderDTO {
	private List<OrderDTO> orders;
}
