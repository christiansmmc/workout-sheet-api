package com.workoutsheet.workoutsheet.repository.record;

import com.workoutsheet.workoutsheet.domain.WorkoutRecordExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRecordExerciseRepository extends JpaRepository<WorkoutRecordExercise, Long> {
}