package com.workoutsheet.workoutsheet.facade.mapper;

import com.workoutsheet.workoutsheet.domain.Exercise;
import com.workoutsheet.workoutsheet.facade.dto.exercise.ExerciseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExerciseMapper {

    ExerciseMapper EXERCISE_MAPPER = Mappers.getMapper(ExerciseMapper.class);

    ExerciseDTO toDto(Exercise exercise);
}
