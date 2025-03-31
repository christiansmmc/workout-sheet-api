package com.workoutsheet.workoutsheet.facade.dto.workout;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutToUpdateListOrderDTO {

    @NotNull
    private Long id;

    @NotNull
    private int listOrder;
}
