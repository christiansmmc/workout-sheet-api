package com.workoutsheet.workoutsheet.repository.record;

import com.workoutsheet.workoutsheet.domain.WorkoutRecordExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRecordExerciseSetRepository extends JpaRepository<WorkoutRecordExerciseSet, Long> {

    List<WorkoutRecordExerciseSet> findAllByWorkoutRecordExerciseId(Long workoutRecordExerciseId);
}