package com.workoutsheet.workoutsheet.facade.vm.workout.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkoutVM {

    @NotBlank
    private String workoutName;

    @Valid
    @NotEmpty
    private List<ExerciseToCreateWorkoutVM> exercises;
}