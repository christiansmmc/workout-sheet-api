package com.workoutsheet.workoutsheet.facade.mapper;

import com.workoutsheet.workoutsheet.domain.Workout;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutDTO;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutIdDTO;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutToUpdateListOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkoutMapper {

    WorkoutMapper WORKOUT_MAPPER = Mappers.getMapper(WorkoutMapper.class);

    WorkoutIdDTO toIdDTO(Workout workout);

    WorkoutDTO toDTO(Workout workout);

    Workout toEntity(WorkoutToUpdateListOrderDTO dto);
}
