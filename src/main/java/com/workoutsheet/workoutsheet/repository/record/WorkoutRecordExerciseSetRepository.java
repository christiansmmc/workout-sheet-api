package com.workoutsheet.workoutsheet.repository.record;

import com.workoutsheet.workoutsheet.domain.WorkoutRecordExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRecordExerciseSetRepository extends JpaRepository<WorkoutRecordExerciseSet, Long> {
}