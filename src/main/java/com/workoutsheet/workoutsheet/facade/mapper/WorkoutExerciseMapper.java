package com.workoutsheet.workoutsheet.facade.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkoutExerciseMapper {

    WorkoutExerciseMapper WORKOUT_EXERCISE_MAPPER = Mappers.getMapper(WorkoutExerciseMapper.class);

}
