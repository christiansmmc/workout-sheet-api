package com.workoutsheet.workoutsheet.service;

import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.Exercise;
import com.workoutsheet.workoutsheet.domain.Workout;
import com.workoutsheet.workoutsheet.domain.WorkoutExercise;
import com.workoutsheet.workoutsheet.facade.vm.workout.create.CreateWorkoutVM;
import com.workoutsheet.workoutsheet.facade.vm.workout.find.ExerciseToFindAllWorkoutExercisesVM;
import com.workoutsheet.workoutsheet.facade.vm.workout.find.FindAllWorkoutExercisesVM;
import com.workoutsheet.workoutsheet.facade.vm.workout.find.WorkoutExeciseToFindAllWorkoutExercisesVM;
import com.workoutsheet.workoutsheet.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository repository;

    private final ClientService clientService;
    private final ExerciseService exerciseService;
    private final WorkoutExerciseService workoutExerciseService;

    public Workout createWorkout(CreateWorkoutVM vm) {
        Client client = clientService.getLoggedUser();

        Workout workout = Workout
                .builder()
                .name(vm.getWorkoutName())
                .client(client)
                .build();

        Workout workoutCreated = repository.save(workout);

        vm.getExercises().forEach(it -> {
            Exercise exercise = exerciseService.findExerciseById(it.getExerciseId());

            WorkoutExercise workoutExercise = WorkoutExercise
                    .builder()
                    .reps(it.getReps())
                    .sets(it.getSets())
                    .exerciseLoad(Optional.ofNullable(it.getLoad()).orElse(BigDecimal.ZERO))
                    .exercise(exercise)
                    .workout(workoutCreated)
                    .build();

            workoutExerciseService.save(workoutExercise);

            // TODO CREATE HISTORY
        });

        return workoutCreated;
    }

    public List<Workout> findAllWorkoutByClient() {
        Client client = clientService.getLoggedUser();
        return repository.findAllByClientId(client.getId());
    }

    public FindAllWorkoutExercisesVM findAllWorkoutExercises(Long id) {
        Client client = clientService.getLoggedUser();

        Workout workout = repository.findById(id)
                .orElseThrow();

        if (!workout.getClient().equals(client)) {
            throw new RuntimeException();
        }

        List<WorkoutExercise> workoutExercises = workoutExerciseService.findAllByWorkout(workout.getId());
        List<WorkoutExeciseToFindAllWorkoutExercisesVM> workoutExercisesVMS = workoutExercises
                .stream()
                .map(workoutExercise -> {
                    ExerciseToFindAllWorkoutExercisesVM exercise = ExerciseToFindAllWorkoutExercisesVM
                            .builder()
                            .id(workoutExercise.getExercise().getId())
                            .name(workoutExercise.getExercise().getName())
                            .bodyPart(workoutExercise.getExercise().getBodyPart())
                            .build();

                    return WorkoutExeciseToFindAllWorkoutExercisesVM
                            .builder()
                            .id(workoutExercise.getId())
                            .sets(workoutExercise.getSets())
                            .reps(workoutExercise.getReps())
                            .exerciseLoad(workoutExercise.getExerciseLoad())
                            .exercise(exercise)
                            .build();
                })
                .toList();

        return FindAllWorkoutExercisesVM
                .builder()
                .id(workout.getId())
                .name(workout.getName())
                .workoutExercises(workoutExercisesVMS)
                .build();
    }
}
