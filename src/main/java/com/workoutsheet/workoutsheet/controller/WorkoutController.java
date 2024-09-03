package com.workoutsheet.workoutsheet.controller;

import com.workoutsheet.workoutsheet.facade.WorkoutFacade;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutDTO;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutIdDTO;
import com.workoutsheet.workoutsheet.facade.vm.workout.create.CreateWorkoutVM;
import com.workoutsheet.workoutsheet.facade.vm.workout.find.FindAllWorkoutExercisesVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutFacade facade;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<WorkoutIdDTO> createWorkout(
            @RequestBody @Valid CreateWorkoutVM vm
    ) throws URISyntaxException {
        WorkoutIdDTO response = facade.createWorkout(vm);

        return ResponseEntity
                .created(new URI("/api/workouts/" + response.getId()))
                .body(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<WorkoutDTO>> findAllWorkoutByClient() {
        List<WorkoutDTO> response = facade.findAllWorkoutByClient();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<FindAllWorkoutExercisesVM> findAllWorkoutExercises(
            @PathVariable Long id
    ) {
        FindAllWorkoutExercisesVM response = facade.findAllWorkoutExercises(id);
        return ResponseEntity.ok().body(response);
    }
}
