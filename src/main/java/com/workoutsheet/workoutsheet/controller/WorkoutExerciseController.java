package com.workoutsheet.workoutsheet.controller;

import com.workoutsheet.workoutsheet.facade.WorkoutExerciseFacade;
import com.workoutsheet.workoutsheet.facade.dto.workout.exercise.WorkoutExerciseToUpdateLoadDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workout-exercises")
@RequiredArgsConstructor
public class WorkoutExerciseController {

    private final WorkoutExerciseFacade facade;

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> updateLoad(
            @PathVariable Long id,
            @RequestBody @Valid WorkoutExerciseToUpdateLoadDTO dto
    ) {
        facade.updateLoad(id, dto);
        return ResponseEntity.ok().build();
    }
}
