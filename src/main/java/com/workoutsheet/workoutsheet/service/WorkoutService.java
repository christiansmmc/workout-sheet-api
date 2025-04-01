package com.workoutsheet.workoutsheet.service;

import com.workoutsheet.workoutsheet.constants.ErrorType;
import com.workoutsheet.workoutsheet.domain.Workout;
import com.workoutsheet.workoutsheet.exception.AppException;
import com.workoutsheet.workoutsheet.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository repository;

    public Workout save(Workout workout) {
        return repository.save(workout);
    }

    public Workout findById(Long id) {
        return repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new AppException(ErrorType.WORKOUT_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public List<Workout> findAllByClientId(Long clientId) {
        return repository.findAllByClientIdAndDeletedAtIsNull(clientId);
    }

    public Optional<Integer> findLastListOrderCreated(Long clientId) {
        return repository.findMaxListOrderByClientId(clientId);
    }
}
