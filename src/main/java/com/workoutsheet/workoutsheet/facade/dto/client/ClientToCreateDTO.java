package com.workoutsheet.workoutsheet.facade.dto.client;

import com.workoutsheet.workoutsheet.facade.dto.user.UserToCreateDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientToCreateDTO {

    @NotBlank
    private String firstName;

    private String lastName;

    private BigDecimal weight;

    private BigDecimal height;

    @Valid
    @NotNull
    private UserToCreateDTO user;
}
