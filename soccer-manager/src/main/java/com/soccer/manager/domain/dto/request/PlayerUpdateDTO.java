package com.soccer.manager.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerUpdateDTO {

	private String id;
	private String firstName;
	private String lastName;
	private String country;
}
