package com.workoutsheet.workoutsheet.facade.vm.workoutrecord.create;

import com.workoutsheet.workoutsheet.domain.enumeration.WorkoutRecordExerciseStatus;
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
public class WorkoutRecordExerciseToCreateWorkoutRecordVM {

    private String note;

    @NotNull
    private WorkoutRecordExerciseStatus status;

    @NotNull
    private Long exerciseId;

    private List<WorkoutRecordExerciseSetToCreateWorkoutRecordVM> exerciseSets;
}
