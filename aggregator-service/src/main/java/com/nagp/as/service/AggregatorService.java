package com.nagp.as.service;

import com.nagp.as.dto.OrderDetailsDTO;

public interface AggregatorService {
	OrderDetailsDTO getUserOrderDetails(Long userId);
}
