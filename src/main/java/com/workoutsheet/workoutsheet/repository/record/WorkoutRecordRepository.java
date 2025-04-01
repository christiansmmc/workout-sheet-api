package com.workoutsheet.workoutsheet.repository.record;

import com.workoutsheet.workoutsheet.domain.WorkoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRecordRepository extends JpaRepository<WorkoutRecord, Long> {
}