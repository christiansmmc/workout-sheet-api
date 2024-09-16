package com.workoutsheet.workoutsheet.facade.dto.workout.exercise;

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
public class WorkoutExerciseToUpdateLoadDTO {

    @NotNull
    private Integer sets;

    @NotNull
    private Integer reps;

    @NotNull
    private BigDecimal load;
}
