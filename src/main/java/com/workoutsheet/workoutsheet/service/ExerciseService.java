package com.workoutsheet.workoutsheet.service;

import com.workoutsheet.workoutsheet.domain.Exercise;
import com.workoutsheet.workoutsheet.domain.enumeration.BodyPart;
import com.workoutsheet.workoutsheet.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository repository;

    public Exercise findExerciseById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

    public List<Exercise> findAllExercises(List<BodyPart> bodyParts) {
        if (bodyParts != null && !bodyParts.isEmpty()) {
            return repository.findAllByBodyPartInOrderByBodyPart(bodyParts);
        }

        return repository.findAllByOrderByBodyPart();
    }
}