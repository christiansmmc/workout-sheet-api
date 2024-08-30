package com.workoutsheet.workoutsheet.controller;

import com.workoutsheet.workoutsheet.config.JwtService;
import com.workoutsheet.workoutsheet.domain.Role;
import com.workoutsheet.workoutsheet.domain.User;
import com.workoutsheet.workoutsheet.facade.dto.AuthenticateRequestDTO;
import com.workoutsheet.workoutsheet.facade.dto.AuthenticateResponseDTO;
import com.workoutsheet.workoutsheet.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<AuthenticateResponseDTO> authenticate(
            @RequestBody @Valid AuthenticateRequestDTO dto
    ) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow();

        Map<String, Object> roles = new HashMap<>();
        roles.put("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));

        String jwtToken = jwtService.generateJwtToken(roles, user);

        AuthenticateResponseDTO response = AuthenticateResponseDTO
                .builder()
                .token(jwtToken)
                .build();

        return ResponseEntity.ok().body(response);
    }
}
