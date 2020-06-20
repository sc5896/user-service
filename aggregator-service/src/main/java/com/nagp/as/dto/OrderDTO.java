package com.nagp.as.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Data transfer object for order details
 * 
 * @author santoshkumar02
 *
 */
@Data
public class OrderDTO {
	private Long orderId;
	private int orderAmount;
	@JsonFormat(pattern = "dd-MMM-yyyy", timezone = "UTC")
	private Date orderDate;
}
