package com.workoutsheet.workoutsheet.service;

import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.WorkoutExercise;
import com.workoutsheet.workoutsheet.exception.AppException;
import com.workoutsheet.workoutsheet.repository.WorkoutExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.workoutsheet.workoutsheet.constants.ErrorType.CLIENT_DONT_HAVE_ACCESS;
import static com.workoutsheet.workoutsheet.constants.ErrorType.WORKOUT_EXERCISE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class WorkoutExerciseService {

    private final WorkoutExerciseRepository repository;
    private final ClientService clientService;

    public void save(WorkoutExercise workoutExercise) {
        repository.save(workoutExercise);
    }

    public List<WorkoutExercise> findAllByWorkout(Long workoutId) {
        return repository.findAllByWorkoutId(workoutId);
    }

    public void updateLoad(Long id, BigDecimal load) {
        Client client = clientService.getLoggedUser();

        WorkoutExercise workoutExercise = repository.findById(id)
                .orElseThrow(() -> new AppException(WORKOUT_EXERCISE_NOT_FOUND, HttpStatus.NOT_FOUND));

        AppException.throwIfNot(
                workoutExercise.getWorkout().getClient().equals(client),
                CLIENT_DONT_HAVE_ACCESS
        );

        if (workoutExercise.getExerciseLoad().equals(load)) {
            return;
        }

        workoutExercise.setExerciseLoad(load);
        repository.save(workoutExercise);

        // TODO UPDATE LOAD HISTORY
    }
}
