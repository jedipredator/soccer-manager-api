package com.soccer.manager.security.jwt;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
}
