package com.workoutsheet.workoutsheet.repository.record;

import com.workoutsheet.workoutsheet.domain.ExerciseLoadRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseLoadRecordRepository extends JpaRepository<ExerciseLoadRecord, Long> {
}
