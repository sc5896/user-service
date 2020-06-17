package com.nagp.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserDTO {
	@JsonIgnore
	private Long id;
	private String name;
	private Integer age;
	private String email;
}
