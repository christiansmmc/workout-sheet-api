package com.workoutsheet.workoutsheet.facade.vm.workout.find;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindAllWorkoutExercisesVM {

    private Long id;

    private String name;

    private List<WorkoutExeciseToFindAllWorkoutExercisesVM> workoutExercises;
}
