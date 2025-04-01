package com.workoutsheet.workoutsheet.facade.vm.workoutrecord.find;

import com.workoutsheet.workoutsheet.domain.enumeration.WorkoutRecordExerciseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutRecordExerciseToFindWorkoutRecordVM {

    private Long id;

    private String note;

    private WorkoutRecordExerciseStatus status;

    private ExerciseToFindWorkoutRecordVM exercise;

    private List<WorkoutRecordExerciseSetToFindWorkoutRecordVM> workoutRecordExerciseSets;
}
