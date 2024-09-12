package com.workoutsheet.workoutsheet.context;

import com.workoutsheet.workoutsheet.constants.ErrorType;
import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.Exercise;
import com.workoutsheet.workoutsheet.domain.Workout;
import com.workoutsheet.workoutsheet.domain.WorkoutExercise;
import com.workoutsheet.workoutsheet.exception.AppException;
import com.workoutsheet.workoutsheet.service.ClientService;
import com.workoutsheet.workoutsheet.service.ExerciseService;
import com.workoutsheet.workoutsheet.service.WorkoutExerciseService;
import com.workoutsheet.workoutsheet.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.workoutsheet.workoutsheet.constants.ErrorType.CLIENT_DONT_HAVE_ACCESS;

@Service
@RequiredArgsConstructor
public class WorkoutExerciseContext {

    private final WorkoutExerciseService service;
    private final ClientService clientService;
    private final ExerciseService exerciseService;
    private final WorkoutService workoutService;

    public void updateLoad(Long id, BigDecimal load) {
        Client client = clientService.getLoggedUser();

        WorkoutExercise workoutExercise = service.findById(id);

        AppException.throwIfNot(
                workoutExercise.getWorkout().getClient().equals(client),
                CLIENT_DONT_HAVE_ACCESS
        );

        if (workoutExercise.getExerciseLoad().equals(load)) {
            return;
        }

        workoutExercise.setExerciseLoad(load);
        service.save(workoutExercise);

        // TODO UPDATE LOAD HISTORY
    }

    public void delete(Long id) {
        Client loggedClient = clientService.getLoggedUser();

        service.findOptionalById(id)
                .ifPresent(workoutExercise -> {
                    if (workoutExercise.getWorkout().getClient().equals(loggedClient)) {
                        service.delete(workoutExercise.getId());
                    }
                });
    }

    public void create(WorkoutExercise workoutExercise) {
        Client loggedClient = clientService.getLoggedUser();
        Workout workout = workoutService.findById(workoutExercise.getWorkout().getId());
        Exercise exercise = exerciseService.findExerciseById(workoutExercise.getExercise().getId());

        AppException.throwIfNot(
                workout.getClient().equals(loggedClient),
                ErrorType.WORKOUT_NOT_FOUND
        );

        WorkoutExercise workoutExerciseToCreate = WorkoutExercise
                .builder()
                .reps(workoutExercise.getReps())
                .sets(workoutExercise.getSets())
                .exerciseLoad(Optional.ofNullable(workoutExercise.getExerciseLoad()).orElse(BigDecimal.ZERO))
                .workout(workout)
                .exercise(exercise)
                .build();

        service.save(workoutExerciseToCreate);

        // TODO CREATE HISTORY
    }

    public void deleteAllFromWorkout(Long workoutId) {
        List<WorkoutExercise> workoutExercises = service.findAllByWorkoutId(workoutId);
        workoutExercises.forEach(workoutExercise -> service.delete(workoutExercise.getId()));
    }
}
