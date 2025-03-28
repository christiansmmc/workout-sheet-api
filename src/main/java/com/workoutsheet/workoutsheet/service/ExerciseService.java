package com.workoutsheet.workoutsheet.service;

import com.workoutsheet.workoutsheet.constants.ErrorType;
import com.workoutsheet.workoutsheet.domain.Exercise;
import com.workoutsheet.workoutsheet.domain.enumeration.BodyPart;
import com.workoutsheet.workoutsheet.exception.AppException;
import com.workoutsheet.workoutsheet.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository repository;

    public Exercise findExerciseById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorType.EXERCISE_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public List<Exercise> findAllExercises(List<BodyPart> bodyParts) {
        if (bodyParts != null && !bodyParts.isEmpty()) {
            return repository.findAllByBodyPartInOrderByBodyPart(bodyParts);
        }

        return repository.findAllByOrderByBodyPart();
    }
}
