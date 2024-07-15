package com.soccer.manager.domain.dto.request;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationRequestDTO {

	@Pattern(regexp="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message="username must be email address") 
	private String username;
	
	@Size(min = 6, max = 30, message = "password length should be between 6 and 30 characters")
	private String password;
}
