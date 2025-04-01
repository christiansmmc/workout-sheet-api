package com.workoutsheet.workoutsheet.service.record;

import com.workoutsheet.workoutsheet.domain.WorkoutRecordExercise;
import com.workoutsheet.workoutsheet.repository.record.WorkoutRecordExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutRecordExerciseService {

    private final WorkoutRecordExerciseRepository repository;

    public WorkoutRecordExercise save(WorkoutRecordExercise workoutRecordExercise) {
        return repository.save(workoutRecordExercise);
    }

    public List<WorkoutRecordExercise> findAllByWorkoutRecord(Long workoutRecordId) {
        return repository.findAllByWorkoutRecordId(workoutRecordId);
    }
}
