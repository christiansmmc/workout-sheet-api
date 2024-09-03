package com.workoutsheet.workoutsheet.repository;

import com.workoutsheet.workoutsheet.domain.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {

    List<WorkoutExercise> findAllByWorkoutId(Long workoutId);
}
