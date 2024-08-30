package com.workoutsheet.workoutsheet.controller;

import com.workoutsheet.workoutsheet.facade.ClientFacade;
import com.workoutsheet.workoutsheet.facade.dto.client.ClientIdDTO;
import com.workoutsheet.workoutsheet.facade.dto.client.ClientToCreateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientFacade facade;

    @PostMapping
    public ResponseEntity<ClientIdDTO> createClient(@RequestBody @Valid ClientToCreateDTO dto) {
        ClientIdDTO response = facade.createClient(dto);
        return ResponseEntity.ok().body(response);
    }
}
