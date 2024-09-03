package com.workoutsheet.workoutsheet.facade.vm.workout.find;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutExeciseToFindAllWorkoutExercisesVM {

    private Long id;

    private Integer sets;

    private Integer reps;

    private BigDecimal exerciseLoad;

    private ExerciseToFindAllWorkoutExercisesVM exercise;
}
