package com.workoutsheet.workoutsheet.repository.record;

import com.workoutsheet.workoutsheet.domain.ClientRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRecordRepository extends JpaRepository<ClientRecord, Long> {
}
