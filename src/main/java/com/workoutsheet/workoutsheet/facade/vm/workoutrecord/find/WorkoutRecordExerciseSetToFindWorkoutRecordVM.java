package com.workoutsheet.workoutsheet.facade.vm.workoutrecord.find;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutRecordExerciseSetToFindWorkoutRecordVM {

    private Long id;

    private Integer set;

    private Integer reps;

    private BigDecimal exerciseLoad;

    private String note;
}
