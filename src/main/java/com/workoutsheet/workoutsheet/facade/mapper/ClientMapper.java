package com.workoutsheet.workoutsheet.facade.mapper;

import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.facade.dto.client.ClientIdDTO;
import com.workoutsheet.workoutsheet.facade.dto.client.ClientToCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper CLIENT_MAPPER = Mappers.getMapper(ClientMapper.class);

    ClientIdDTO toClientIdDTO(Client client);

    @Mapping(target = "firstName", expression = "java(dto.getFirstName().toLowerCase())")
    @Mapping(target = "lastName", expression = "java(dto.getFirstName().toLowerCase())")
    Client toClient(ClientToCreateDTO dto);
}
