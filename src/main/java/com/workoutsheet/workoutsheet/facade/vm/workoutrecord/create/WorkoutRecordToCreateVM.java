package com.workoutsheet.workoutsheet.facade.vm.workoutrecord.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutRecordToCreateVM {

    @NotNull
    private Long workoutId;

    @Valid
    @NotNull
    private List<WorkoutRecordExerciseToCreateWorkoutRecordVM> exercises;
}
