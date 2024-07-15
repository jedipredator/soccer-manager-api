package com.soccer.manager.domain.mapper;

import org.mapstruct.Mapper;

import com.soccer.manager.domain.dto.response.UserCreationResponseDTO;
import com.soccer.manager.domain.repo.entity.User;

@Mapper(componentModel = "spring")
public interface UserCreationMapper {

	UserCreationResponseDTO mapToUserCreationResponseDto(User user);
}
