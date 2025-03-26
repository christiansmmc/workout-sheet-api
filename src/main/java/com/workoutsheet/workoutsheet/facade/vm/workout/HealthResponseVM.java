package com.workoutsheet.workoutsheet.facade.vm.workout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthResponseVM {
    
    @Builder.Default
    private String status = "OK";
}
