package com.soccer.manager.domain.mapper;

import java.math.BigDecimal;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.soccer.manager.domain.dto.response.PlayerResponseDTO;
import com.soccer.manager.domain.dto.response.TeamResponseDTO;
import com.soccer.manager.domain.repo.entity.Team;

@Mapper(componentModel = "spring",  uses = {
        PlayerMapper.class, TransferListMapper.class
})
public interface TeamMapper {

	TeamResponseDTO mapToTeamResponseDto(Team team);
	
	@AfterMapping
	default void putTeamValue(Team team, @MappingTarget TeamResponseDTO teamResponseDTO) {
		teamResponseDTO.setValue(teamResponseDTO.getPlayers().stream()
									.map(PlayerResponseDTO::getValue)
									.reduce(BigDecimal.ZERO, (v1, v2) -> v1.add(v2)));
	}
}
