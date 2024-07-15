package com.soccer.manager.domain.dto.response;

import java.math.BigDecimal;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamResponseDTO {

	private String id;
	private String name;
	private String country;
	private BigDecimal budgetAmount;
	private String budgetCurrency;
	private BigDecimal value;
	private Set<PlayerResponseDTO> players;
}
