package com.workoutsheet.workoutsheet.controller;

import com.workoutsheet.workoutsheet.domain.enumeration.BodyPart;
import com.workoutsheet.workoutsheet.facade.ExerciseFacade;
import com.workoutsheet.workoutsheet.facade.dto.exercise.ExerciseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseFacade facade;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ExerciseDTO>> findAllExercises(
            @RequestParam(name = "body_part", required = false) List<BodyPart> bodyParts
    ) {
        List<ExerciseDTO> response = facade.findAll(bodyParts);
        return ResponseEntity.ok().body(response);
    }
}
