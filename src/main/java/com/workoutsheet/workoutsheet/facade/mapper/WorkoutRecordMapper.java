package com.workoutsheet.workoutsheet.facade.mapper;

import com.workoutsheet.workoutsheet.domain.WorkoutRecord;
import com.workoutsheet.workoutsheet.facade.dto.WorkoutRecordToFindAllSimpleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkoutRecordMapper {

    WorkoutRecordMapper WORKOUT_RECORD_MAPPER = Mappers.getMapper(WorkoutRecordMapper.class);

    WorkoutRecordToFindAllSimpleDTO toFindAllSimpleDTO(WorkoutRecord entity);
}
