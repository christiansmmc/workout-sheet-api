package com.workoutsheet.workoutsheet.facade;

import com.workoutsheet.workoutsheet.domain.Workout;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutDTO;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutIdDTO;
import com.workoutsheet.workoutsheet.facade.vm.workout.find.FindAllWorkoutExercisesVM;
import com.workoutsheet.workoutsheet.facade.mapper.WorkoutMapper;
import com.workoutsheet.workoutsheet.facade.vm.workout.create.CreateWorkoutVM;
import com.workoutsheet.workoutsheet.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class WorkoutFacade {

    private final WorkoutService service;

    @Transactional
    public WorkoutIdDTO createWorkout(CreateWorkoutVM vm) {
        Workout workout = service.createWorkout(vm);
        return WorkoutMapper.WORKOUT_MAPPER.toIdDTO(workout);
    }

    @Transactional(readOnly = true)
    public List<WorkoutDTO> findAllWorkoutByClient() {
        List<Workout> workouts = service.findAllWorkoutByClient();
        return workouts
                .stream()
                .map(WorkoutMapper.WORKOUT_MAPPER::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public FindAllWorkoutExercisesVM findAllWorkoutExercises(Long id) {
        return service.findAllWorkoutExercises(id);
    }
}
