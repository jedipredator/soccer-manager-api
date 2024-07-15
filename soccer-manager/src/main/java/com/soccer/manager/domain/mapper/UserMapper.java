package com.soccer.manager.domain.mapper;

import org.mapstruct.Mapper;

import com.soccer.manager.domain.dto.response.UserResponseDTO;
import com.soccer.manager.domain.repo.entity.User;

@Mapper(componentModel = "spring", uses = {
        TeamMapper.class, PlayerMapper.class, TransferListMapper.class
})
public interface UserMapper {

	UserResponseDTO mapToUserResponseDto(User user);
}
