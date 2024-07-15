package com.soccer.manager.domain.dto.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferListRequestDTO {

	private String playerId;
	private BigDecimal price;
}