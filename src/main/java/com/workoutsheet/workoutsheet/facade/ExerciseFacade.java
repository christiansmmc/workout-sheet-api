package com.workoutsheet.workoutsheet.facade;

import com.workoutsheet.workoutsheet.domain.enumeration.BodyPart;
import com.workoutsheet.workoutsheet.facade.dto.exercise.ExerciseDTO;
import com.workoutsheet.workoutsheet.facade.mapper.ExerciseMapper;
import com.workoutsheet.workoutsheet.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class ExerciseFacade {

    private final ExerciseService service;

    @Transactional(readOnly = true)
    public List<ExerciseDTO> findAll(List<BodyPart> bodyParts) {
        return service.findAllExercises(bodyParts)
                .stream()
                .map(ExerciseMapper.EXERCISE_MAPPER::toDto)
                .toList();
    }
}
