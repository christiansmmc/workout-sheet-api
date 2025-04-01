package com.workoutsheet.workoutsheet.facade.dto;

import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutRecordToFindAllSimpleDTO {

    private Long id;

    private LocalDate date;

    private WorkoutDTO workout;
}
