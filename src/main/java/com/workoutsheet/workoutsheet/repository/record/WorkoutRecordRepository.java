package com.workoutsheet.workoutsheet.repository.record;

import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.Workout;
import com.workoutsheet.workoutsheet.domain.WorkoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkoutRecordRepository extends
        JpaRepository<WorkoutRecord, Long>,
        JpaSpecificationExecutor<WorkoutRecord> {

    Optional<WorkoutRecord> findTopByWorkoutAndWorkout_ClientOrderByDateDesc(
            Workout workout,
            Client client
    );
}