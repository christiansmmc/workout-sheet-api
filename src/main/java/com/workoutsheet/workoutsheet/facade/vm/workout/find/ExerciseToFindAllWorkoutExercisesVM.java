package com.workoutsheet.workoutsheet.facade.vm.workout.find;

import com.workoutsheet.workoutsheet.domain.enumeration.BodyPart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseToFindAllWorkoutExercisesVM {

    private Long id;

    private String name;

    private BodyPart bodyPart;
}
