package com.workoutsheet.workoutsheet.controller;

import com.workoutsheet.workoutsheet.facade.WorkoutExerciseFacade;
import com.workoutsheet.workoutsheet.facade.dto.workout.exercise.WorkoutExerciseToCreateDTO;
import com.workoutsheet.workoutsheet.facade.dto.workout.exercise.WorkoutExerciseToUpdateLoadDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        facade.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> addExerciseToWorkout(
            @RequestBody @Valid WorkoutExerciseToCreateDTO dto
    ) throws URISyntaxException {
        facade.addExerciseToWorkout(dto);
        return ResponseEntity
                .created(new URI("/api/workout-exercises/"))
                .build();
    }
}
