package com.workoutsheet.workoutsheet.facade.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserToCreateDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
