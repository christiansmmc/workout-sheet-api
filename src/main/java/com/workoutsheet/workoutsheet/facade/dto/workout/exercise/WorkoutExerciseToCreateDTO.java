package com.workoutsheet.workoutsheet.facade.dto.workout.exercise;

import com.workoutsheet.workoutsheet.facade.dto.exercise.ExerciseIdDTO;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutIdDTO;
import jakarta.validation.Valid;
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
public class WorkoutExerciseToCreateDTO {

    private Integer sets;

    private Integer reps;

    private BigDecimal exerciseLoad = BigDecimal.ZERO;

    @Valid
    @NotNull
    private WorkoutIdDTO workout;

    @Valid
    @NotNull
    private ExerciseIdDTO exercise;
}
