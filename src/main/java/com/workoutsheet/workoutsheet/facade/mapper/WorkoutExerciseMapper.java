package com.workoutsheet.workoutsheet.facade.mapper;

import com.workoutsheet.workoutsheet.domain.WorkoutExercise;
import com.workoutsheet.workoutsheet.facade.dto.workout.exercise.WorkoutExerciseToCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkoutExerciseMapper {

    WorkoutExerciseMapper WORKOUT_EXERCISE_MAPPER = Mappers.getMapper(WorkoutExerciseMapper.class);

    WorkoutExercise toEntity(WorkoutExerciseToCreateDTO dto);
}
