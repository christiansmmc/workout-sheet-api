package com.workoutsheet.workoutsheet.repository.record;

import com.workoutsheet.workoutsheet.domain.ClientHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientHistoryRepository extends JpaRepository<ClientHistory, Long> {
}
