package com.workoutsheet.workoutsheet.facade.vm.workoutrecord.find;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutRecordToFindWorkoutRecordVM {

    private Long id;

    private LocalDate date;

    private WorkoutToFindWorkoutRecordVM workout;

    private List<WorkoutRecordExerciseToFindWorkoutRecordVM> workoutRecordExercises;
}
