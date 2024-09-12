package com.workoutsheet.workoutsheet.context;

import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.Exercise;
import com.workoutsheet.workoutsheet.domain.Workout;
import com.workoutsheet.workoutsheet.domain.WorkoutExercise;
import com.workoutsheet.workoutsheet.exception.AppException;
import com.workoutsheet.workoutsheet.facade.vm.workout.create.CreateWorkoutVM;
import com.workoutsheet.workoutsheet.facade.vm.workout.create.ExerciseToCreateWorkoutVM;
import com.workoutsheet.workoutsheet.facade.vm.workout.find.ExerciseToFindAllWorkoutExercisesVM;
import com.workoutsheet.workoutsheet.facade.vm.workout.find.FindAllWorkoutExercisesVM;
import com.workoutsheet.workoutsheet.facade.vm.workout.find.WorkoutExeciseToFindAllWorkoutExercisesVM;
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
public class WorkoutContext {

    private final WorkoutService service;
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

        Workout workoutCreated = service.save(workout);
        this.createWorkoutExercises(vm.getExercises(), workoutCreated);

        return workoutCreated;
    }

    private void createWorkoutExercises(
            List<ExerciseToCreateWorkoutVM> exercises,
            Workout workout
    ) {
        exercises.forEach(it -> {
            Exercise exercise = exerciseService.findExerciseById(it.getExerciseId());

            WorkoutExercise workoutExercise = WorkoutExercise
                    .builder()
                    .reps(it.getReps())
                    .sets(it.getSets())
                    .exerciseLoad(Optional.ofNullable(it.getLoad()).orElse(BigDecimal.ZERO))
                    .exercise(exercise)
                    .workout(workout)
                    .build();

            workoutExerciseService.save(workoutExercise);

            // TODO CREATE HISTORY
        });
    }

    public List<Workout> findAllWorkoutByClient() {
        Client client = clientService.getLoggedUser();
        return service.findAllByClientId(client.getId());
    }

    public FindAllWorkoutExercisesVM findAllWorkoutExercises(Long id) {
        Workout workout = this.getLoggedClientWorkoutById(id);

        List<WorkoutExercise> workoutExercises = workoutExerciseService.findAllByWorkoutId(workout.getId());
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

    public void updateWorkout(Long id, String name) {
        Workout workout = this.getLoggedClientWorkoutById(id);
        workout.setName(name);

        service.save(workout);
    }

    public void deleteWorkout(Long id) {
        Workout workout = this.getLoggedClientWorkoutById(id);

        workoutExerciseService.deleteAllFromWorkout(workout.getId());
        service.delete(workout.getId());
    }

    private Workout getLoggedClientWorkoutById(Long workoutId) {
        Client client = clientService.getLoggedUser();
        Workout workout = service.findById(workoutId);

        AppException.throwIfNot(
                workout.getClient().equals(client),
                CLIENT_DONT_HAVE_ACCESS
        );

        return workout;
    }
}
