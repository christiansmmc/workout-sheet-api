package com.workoutsheet.workoutsheet.facade.vm.workoutrecord.find;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutToFindWorkoutRecordVM {

    private Long id;

    private String name;
}
