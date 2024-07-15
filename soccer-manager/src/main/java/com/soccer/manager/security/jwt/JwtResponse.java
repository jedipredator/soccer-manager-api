package com.soccer.manager.security.jwt;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String userId;
	private final String jwttoken;
}
