package com.workoutsheet.workoutsheet.facade;

import com.workoutsheet.workoutsheet.context.WorkoutExerciseContext;
import com.workoutsheet.workoutsheet.domain.WorkoutExercise;
import com.workoutsheet.workoutsheet.facade.dto.workout.exercise.WorkoutExerciseToCreateDTO;
import com.workoutsheet.workoutsheet.facade.dto.workout.exercise.WorkoutExerciseToUpdateLoadDTO;
import com.workoutsheet.workoutsheet.facade.mapper.WorkoutExerciseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseFacade {

    private final WorkoutExerciseContext context;

    @Transactional
    public void updateLoad(Long id, WorkoutExerciseToUpdateLoadDTO dto) {
        context.updateLoad(id, dto.getLoad());
    }

    @Transactional
    public void delete(Long id) {
        context.delete(id);
    }

    @Transactional
    public void create(WorkoutExerciseToCreateDTO dto) {
        WorkoutExercise workoutExercise = WorkoutExerciseMapper.WORKOUT_EXERCISE_MAPPER.toEntity(dto);
        context.create(workoutExercise);
    }
}
