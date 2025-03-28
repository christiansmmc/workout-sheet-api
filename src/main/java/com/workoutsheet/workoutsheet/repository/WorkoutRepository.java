package com.workoutsheet.workoutsheet.repository;

import com.workoutsheet.workoutsheet.domain.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findAllByClientId(Long clientId);

    @Query("SELECT MAX(w.listOrder) FROM Workout w WHERE w.client.id = :clientId")
    Optional<Integer> findMaxListOrderByClientId(@Param("clientId") Long clientId);

}
