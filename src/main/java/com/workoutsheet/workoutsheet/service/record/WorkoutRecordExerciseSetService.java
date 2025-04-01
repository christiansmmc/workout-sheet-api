package com.workoutsheet.workoutsheet.service.record;

import com.workoutsheet.workoutsheet.domain.WorkoutRecordExerciseSet;
import com.workoutsheet.workoutsheet.repository.record.WorkoutRecordExerciseSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkoutRecordExerciseSetService {

    private final WorkoutRecordExerciseSetRepository repository;

    public WorkoutRecordExerciseSet save(WorkoutRecordExerciseSet workoutRecordExerciseSet) {
        return repository.save(workoutRecordExerciseSet);
    }
}
