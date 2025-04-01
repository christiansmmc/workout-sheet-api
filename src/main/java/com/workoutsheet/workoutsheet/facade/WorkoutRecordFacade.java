package com.workoutsheet.workoutsheet.facade;

import com.workoutsheet.workoutsheet.context.WorkoutRecordContext;
import com.workoutsheet.workoutsheet.domain.WorkoutRecord;
import com.workoutsheet.workoutsheet.facade.dto.WorkoutRecordToFindAllSimpleDTO;
import com.workoutsheet.workoutsheet.facade.mapper.WorkoutRecordMapper;
import com.workoutsheet.workoutsheet.facade.vm.FindWorkoutRecordFilterParamsVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.create.WorkoutRecordToCreateVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.find.WorkoutRecordToFindWorkoutRecordVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class WorkoutRecordFacade {

    private final WorkoutRecordContext context;

    @Transactional
    public WorkoutRecordToFindWorkoutRecordVM create(WorkoutRecordToCreateVM vm) {
        return context.create(vm);
    }

    @Transactional(readOnly = true)
    public List<WorkoutRecordToFindAllSimpleDTO> findAllSimpleFiltered(
            FindWorkoutRecordFilterParamsVM filterParams
    ) {
        List<WorkoutRecord> workoutRecords = context.findAllSimpleFiltered(filterParams);
        return workoutRecords
                .stream()
                .map(WorkoutRecordMapper.WORKOUT_RECORD_MAPPER::toFindAllSimpleDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<WorkoutRecordToFindWorkoutRecordVM> findLastFromWorkout(Long workoutId) {
        return context.findLastFromWorkout(workoutId);
    }
}
