package com.workoutsheet.workoutsheet.facade.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateRequestDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
