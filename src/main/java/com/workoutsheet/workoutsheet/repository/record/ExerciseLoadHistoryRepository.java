package com.workoutsheet.workoutsheet.repository.record;

import com.workoutsheet.workoutsheet.domain.ExerciseLoadHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseLoadHistoryRepository extends JpaRepository<ExerciseLoadHistory, Long> {
}
