package com.workoutsheet.workoutsheet.service.record;

import com.workoutsheet.workoutsheet.domain.WorkoutRecordExercise;
import com.workoutsheet.workoutsheet.repository.record.WorkoutRecordExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkoutRecordExerciseService {

    private final WorkoutRecordExerciseRepository repository;

    public WorkoutRecordExercise save(WorkoutRecordExercise workoutRecordExercise) {
        return repository.save(workoutRecordExercise);
    }
}
