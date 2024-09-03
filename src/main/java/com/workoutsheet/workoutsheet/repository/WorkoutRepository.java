package com.workoutsheet.workoutsheet.repository;

import com.workoutsheet.workoutsheet.domain.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findAllByClientId(Long clientId);
}
