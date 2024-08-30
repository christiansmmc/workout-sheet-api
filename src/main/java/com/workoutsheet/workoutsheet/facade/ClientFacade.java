package com.workoutsheet.workoutsheet.facade;

import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.facade.dto.client.ClientIdDTO;
import com.workoutsheet.workoutsheet.facade.dto.client.ClientToCreateDTO;
import com.workoutsheet.workoutsheet.facade.mapper.ClientMapper;
import com.workoutsheet.workoutsheet.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientFacade {

    private final ClientService service;

    @Transactional
    public ClientIdDTO createClient(ClientToCreateDTO dto) {
        Client client = ClientMapper.CLIENT_MAPPER.toClient(dto);
        return ClientMapper.CLIENT_MAPPER.toClientIdDTO(service.createClient(client));
    }
}
