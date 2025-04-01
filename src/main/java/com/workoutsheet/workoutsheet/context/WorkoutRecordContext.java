package com.workoutsheet.workoutsheet.context;

import com.workoutsheet.workoutsheet.constants.ErrorType;
import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.Exercise;
import com.workoutsheet.workoutsheet.domain.Workout;
import com.workoutsheet.workoutsheet.domain.WorkoutRecord;
import com.workoutsheet.workoutsheet.domain.WorkoutRecordExercise;
import com.workoutsheet.workoutsheet.domain.WorkoutRecordExerciseSet;
import com.workoutsheet.workoutsheet.exception.AppException;
import com.workoutsheet.workoutsheet.facade.vm.FindWorkoutRecordFilterParamsVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.create.WorkoutRecordExerciseSetToCreateWorkoutRecordVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.create.WorkoutRecordExerciseToCreateWorkoutRecordVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.create.WorkoutRecordToCreateVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.find.ExerciseToFindWorkoutRecordVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.find.WorkoutRecordExerciseSetToFindWorkoutRecordVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.find.WorkoutRecordExerciseToFindWorkoutRecordVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.find.WorkoutRecordToFindWorkoutRecordVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.find.WorkoutToFindWorkoutRecordVM;
import com.workoutsheet.workoutsheet.service.ClientService;
import com.workoutsheet.workoutsheet.service.ExerciseService;
import com.workoutsheet.workoutsheet.service.WorkoutService;
import com.workoutsheet.workoutsheet.service.record.WorkoutRecordExerciseService;
import com.workoutsheet.workoutsheet.service.record.WorkoutRecordExerciseSetService;
import com.workoutsheet.workoutsheet.service.record.WorkoutRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutRecordContext {

    private final WorkoutRecordService service;
    private final WorkoutRecordExerciseService workoutRecordExerciseService;
    private final WorkoutRecordExerciseSetService workoutRecordExerciseSetService;
    private final WorkoutService workoutService;
    private final ClientService clientService;
    private final ExerciseService exerciseService;

    public WorkoutRecordToFindWorkoutRecordVM create(WorkoutRecordToCreateVM vm) {
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

        return mapToFindWorkoutRecordVM(workoutRecordCreated);
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

    public List<WorkoutRecord> findAllSimpleFiltered(
            FindWorkoutRecordFilterParamsVM filterParams
    ) {
        Client client = clientService.getLoggedUser();

        return service.findAllWithFilter(filterParams, client);
    }

    public Optional<WorkoutRecordToFindWorkoutRecordVM> findLastFromWorkout(Long workoutId) {
        Client client = clientService.getLoggedUser();
        Workout workout = workoutService.findById(workoutId);

        AppException.throwIfNot(
                workout.getClient().equals(client),
                ErrorType.WORKOUT_NOT_FOUND
        );

        Optional<WorkoutRecord> workoutRecordOptional = service.findLastFromWorkout(workout, client);

        if (workoutRecordOptional.isEmpty()) {
            return Optional.empty();
        }

        WorkoutRecord workoutRecord = workoutRecordOptional.get();
        return Optional.of(mapToFindWorkoutRecordVM(workoutRecord));
    }

    private WorkoutRecordToFindWorkoutRecordVM mapToFindWorkoutRecordVM(WorkoutRecord workoutRecord) {
        List<WorkoutRecordExercise> exercises = workoutRecordExerciseService
                .findAllByWorkoutRecord(workoutRecord.getId());

        List<WorkoutRecordExerciseToFindWorkoutRecordVM> exerciseVMs = exercises
                .stream()
                .map(exercise -> {
                    List<WorkoutRecordExerciseSet> sets = workoutRecordExerciseSetService
                            .findAllByWorkoutRecordExercise(exercise.getId());

                    List<WorkoutRecordExerciseSetToFindWorkoutRecordVM> setVMs = sets
                            .stream()
                            .map(set ->
                                    WorkoutRecordExerciseSetToFindWorkoutRecordVM
                                            .builder()
                                            .id(set.getId())
                                            .set(set.getSet())
                                            .reps(set.getReps())
                                            .exerciseLoad(set.getExerciseLoad())
                                            .note(set.getNote())
                                            .build()
                            ).toList();

                    return WorkoutRecordExerciseToFindWorkoutRecordVM
                            .builder()
                            .id(exercise.getId())
                            .note(exercise.getNote())
                            .status(exercise.getStatus())
                            .exercise(ExerciseToFindWorkoutRecordVM.builder()
                                    .id(exercise.getExercise().getId())
                                    .name(exercise.getExercise().getName())
                                    .bodyPart(exercise.getExercise().getBodyPart())
                                    .build())
                            .workoutRecordExerciseSets(setVMs)
                            .build();
                }).toList();

        return WorkoutRecordToFindWorkoutRecordVM
                .builder()
                .id(workoutRecord.getId())
                .date(workoutRecord.getDate())
                .workout(WorkoutToFindWorkoutRecordVM.builder()
                        .id(workoutRecord.getWorkout().getId())
                        .name(workoutRecord.getWorkout().getName())
                        .build())
                .workoutRecordExercises(exerciseVMs)
                .build();
    }
}
