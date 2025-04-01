package com.workoutsheet.workoutsheet.context;

import com.workoutsheet.workoutsheet.constants.ErrorType;
import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.Exercise;
import com.workoutsheet.workoutsheet.domain.Workout;
import com.workoutsheet.workoutsheet.domain.WorkoutRecord;
import com.workoutsheet.workoutsheet.domain.WorkoutRecordExercise;
import com.workoutsheet.workoutsheet.domain.WorkoutRecordExerciseSet;
import com.workoutsheet.workoutsheet.exception.AppException;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.create.WorkoutRecordExerciseSetToCreateWorkoutRecordVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.create.WorkoutRecordExerciseToCreateWorkoutRecordVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.create.WorkoutRecordToCreateVM;
import com.workoutsheet.workoutsheet.service.ClientService;
import com.workoutsheet.workoutsheet.service.ExerciseService;
import com.workoutsheet.workoutsheet.service.WorkoutService;
import com.workoutsheet.workoutsheet.service.record.WorkoutRecordExerciseService;
import com.workoutsheet.workoutsheet.service.record.WorkoutRecordExerciseSetService;
import com.workoutsheet.workoutsheet.service.record.WorkoutRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WorkoutRecordContext {

    private final WorkoutRecordService service;
    private final WorkoutRecordExerciseService workoutRecordExerciseService;
    private final WorkoutRecordExerciseSetService workoutRecordExerciseSetService;
    private final WorkoutService workoutService;
    private final ClientService clientService;
    private final ExerciseService exerciseService;

    public void create(WorkoutRecordToCreateVM vm) {
        Client client = clientService.getLoggedUser();
        Workout workout = workoutService.findById(vm.getWorkoutId());

        AppException.throwIfNot(
                workout.getClient().equals(client),
                ErrorType.WORKOUT_NOT_FOUND
        );

        WorkoutRecord workoutRecord = WorkoutRecord
                .builder()
                .date(LocalDate.now())
                .workout(workout)
                .build();

        WorkoutRecord workoutRecordCreated = service.save(workoutRecord);

        vm.getExercises().forEach(vmExercise ->
                createWorkoutRecordExercise(vmExercise, workoutRecordCreated));
    }

    private void createWorkoutRecordExercise(
            WorkoutRecordExerciseToCreateWorkoutRecordVM vm,
            WorkoutRecord workoutRecord
    ) {
        Exercise exercise = exerciseService.findExerciseById(vm.getExerciseId());

        WorkoutRecordExercise workoutRecordExercise = WorkoutRecordExercise
                .builder()
                .note(vm.getNote())
                .status(vm.getStatus())
                .exercise(exercise)
                .workoutRecord(workoutRecord)
                .build();

        WorkoutRecordExercise workoutRecordExerciseCreated = workoutRecordExerciseService.save(workoutRecordExercise);

        if (vm.getExerciseSets() != null && !vm.getExerciseSets().isEmpty()) {
            vm.getExerciseSets().forEach(exerciseSet -> {
                createWorkoutRecordExerciseSet(exerciseSet, workoutRecordExerciseCreated);
            });
        }
    }

    private void createWorkoutRecordExerciseSet(
            WorkoutRecordExerciseSetToCreateWorkoutRecordVM vm,
            WorkoutRecordExercise workoutRecordExercise
    ) {
        WorkoutRecordExerciseSet workoutRecordExerciseSet = WorkoutRecordExerciseSet
                .builder()
                .set(vm.getSet())
                .reps(vm.getReps())
                .exerciseLoad(vm.getExerciseLoad())
                .note(vm.getNote())
                .workoutRecordExercise(workoutRecordExercise)
                .build();

        workoutRecordExerciseSetService.save(workoutRecordExerciseSet);
    }
}
