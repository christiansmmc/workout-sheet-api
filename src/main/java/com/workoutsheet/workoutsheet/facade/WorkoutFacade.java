package com.workoutsheet.workoutsheet.facade;

import com.workoutsheet.workoutsheet.context.WorkoutContext;
import com.workoutsheet.workoutsheet.domain.Workout;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutDTO;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutIdDTO;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutToUpdateDTO;
import com.workoutsheet.workoutsheet.facade.mapper.WorkoutMapper;
import com.workoutsheet.workoutsheet.facade.vm.workout.create.CreateWorkoutVM;
import com.workoutsheet.workoutsheet.facade.vm.workout.find.FindAllWorkoutExercisesVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class WorkoutFacade {

    private final WorkoutContext context;

    @Transactional
    public WorkoutIdDTO createWorkout(CreateWorkoutVM vm) {
        Workout workout = context.createWorkout(vm);
        return WorkoutMapper.WORKOUT_MAPPER.toIdDTO(workout);
    }

    @Transactional(readOnly = true)
    public List<WorkoutDTO> findAllWorkoutByClient() {
        List<Workout> workouts = context.findAllWorkoutByClient();
        return workouts
                .stream()
                .map(WorkoutMapper.WORKOUT_MAPPER::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public FindAllWorkoutExercisesVM findAllWorkoutExercises(Long id) {
        return context.findAllWorkoutExercises(id);
    }

    @Transactional
    public void updateWorkout(Long id, WorkoutToUpdateDTO dto) {
        context.updateWorkout(id, dto.getName());
    }

    @Transactional
    public void deleteWorkout(Long id) {
        context.deleteWorkout(id);
    }
}
