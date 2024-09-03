package com.workoutsheet.workoutsheet.repository;

import com.workoutsheet.workoutsheet.domain.Exercise;
import com.workoutsheet.workoutsheet.domain.enumeration.BodyPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findAllByOrderByBodyPart();

    List<Exercise> findAllByBodyPartInOrderByBodyPart(List<BodyPart> bodyParts);
}
