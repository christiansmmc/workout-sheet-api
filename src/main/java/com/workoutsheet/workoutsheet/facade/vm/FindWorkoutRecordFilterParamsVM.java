package com.workoutsheet.workoutsheet.facade.vm;

import com.workoutsheet.workoutsheet.facade.enumeration.DateFilterPeriod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindWorkoutRecordFilterParamsVM {

    private Long workoutId;

    private DateFilterPeriod period;
}
