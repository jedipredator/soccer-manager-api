package com.soccer.manager.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

	private String id;
	private String username;
	private TeamResponseDTO team;
}
