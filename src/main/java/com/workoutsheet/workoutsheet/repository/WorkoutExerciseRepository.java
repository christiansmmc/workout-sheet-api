package com.workoutsheet.workoutsheet.repository;

import com.workoutsheet.workoutsheet.domain.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {

    List<WorkoutExercise> findAllByWorkoutId(Long workoutId);

    @Query("SELECT MAX(we.listOrder) FROM WorkoutExercise we WHERE we.id = :workoutId")
    Optional<Integer> findMaxListOrderByWorkoutId(@Param("workoutId") Long workoutId);
}
