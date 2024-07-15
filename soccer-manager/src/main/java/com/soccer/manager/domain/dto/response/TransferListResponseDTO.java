package com.soccer.manager.domain.dto.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferListResponseDTO {

	private String id;
	private String playerId;
    private BigDecimal price;
    private boolean activeOffer;
}
