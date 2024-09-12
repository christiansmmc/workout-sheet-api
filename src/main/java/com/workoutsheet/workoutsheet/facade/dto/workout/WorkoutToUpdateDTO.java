package com.workoutsheet.workoutsheet.facade.dto.workout;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutToUpdateDTO {

    @NotBlank
    private String name;
}
