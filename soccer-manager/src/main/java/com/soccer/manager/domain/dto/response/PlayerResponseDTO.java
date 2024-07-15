package com.soccer.manager.domain.dto.response;

import java.math.BigDecimal;
import java.util.Set;

import com.soccer.manager.domain.repo.entity.PlayerType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerResponseDTO {

	private String id;
    private String firstName;
    private String lastName;
    private String country;
    private int age;
    private BigDecimal value;
    private PlayerType playerType;
    private Set<TransferListResponseDTO> transferLists;
}
