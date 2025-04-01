package com.workoutsheet.workoutsheet.service.record;

import com.workoutsheet.workoutsheet.domain.WorkoutRecord;
import com.workoutsheet.workoutsheet.repository.record.WorkoutRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkoutRecordService {

    private final WorkoutRecordRepository repository;

    public WorkoutRecord save(WorkoutRecord workoutRecord) {
        return repository.save(workoutRecord);
    }
}
