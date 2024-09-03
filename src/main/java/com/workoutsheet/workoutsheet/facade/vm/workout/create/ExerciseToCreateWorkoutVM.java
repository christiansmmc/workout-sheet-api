package com.workoutsheet.workoutsheet.facade.vm.workout.create;

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
public class ExerciseToCreateWorkoutVM {

    @NotNull
    private Long exerciseId;

    private Integer reps;

    private Integer sets;

    private BigDecimal load;
}