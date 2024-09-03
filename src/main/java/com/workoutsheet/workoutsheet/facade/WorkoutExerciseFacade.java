package com.workoutsheet.workoutsheet.facade;

import com.workoutsheet.workoutsheet.facade.dto.workout.exercise.WorkoutExerciseToUpdateLoadDTO;
import com.workoutsheet.workoutsheet.service.WorkoutExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseFacade {

    private final WorkoutExerciseService service;

    @Transactional
    public void updateLoad(Long id, WorkoutExerciseToUpdateLoadDTO dto) {
        service.updateLoad(id, dto.getLoad());
    }
}
