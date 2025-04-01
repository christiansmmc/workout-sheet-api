package com.workoutsheet.workoutsheet.facade.vm.workoutrecord.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutRecordExerciseSetToCreateWorkoutRecordVM {

    private String note;

    private Integer set;

    private Integer reps;

    private BigDecimal exerciseLoad;
}
