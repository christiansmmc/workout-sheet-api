package com.workoutsheet.workoutsheet.service;

import com.workoutsheet.workoutsheet.domain.WorkoutExercise;
import com.workoutsheet.workoutsheet.exception.AppException;
import com.workoutsheet.workoutsheet.repository.WorkoutExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.workoutsheet.workoutsheet.constants.ErrorType.WORKOUT_EXERCISE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class WorkoutExerciseService {

    private final WorkoutExerciseRepository repository;

    public void save(WorkoutExercise workoutExercise) {
        repository.save(workoutExercise);
    }

    public List<WorkoutExercise> findAllByWorkoutId(Long workoutId) {
        return repository.findAllByWorkoutId(workoutId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllFromWorkout(Long workoutId) {
        List<WorkoutExercise> workoutExercises = repository.findAllByWorkoutId(workoutId);
        repository.deleteAll(workoutExercises);
    }

    public WorkoutExercise findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(WORKOUT_EXERCISE_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public Optional<WorkoutExercise> findOptionalById(Long id) {
        return repository.findById(id);
    }
}
