package com.workoutsheet.workoutsheet.facade.vm.workout.find;

import com.workoutsheet.workoutsheet.domain.enumeration.BodyPart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindAllWorkoutVM {

    private Long id;

    private String name;

    private int listOrder;

    private Set<BodyPart> bodyParts;
}
