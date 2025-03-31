package com.workoutsheet.workoutsheet.controller;

import com.workoutsheet.workoutsheet.facade.WorkoutFacade;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutDTO;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutIdDTO;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutToUpdateDTO;
import com.workoutsheet.workoutsheet.facade.dto.workout.WorkoutToUpdateListOrderDTO;
import com.workoutsheet.workoutsheet.facade.vm.workout.create.CreateWorkoutVM;
import com.workoutsheet.workoutsheet.facade.vm.workout.find.FindAllWorkoutExercisesVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> updateWorkout(
            @PathVariable Long id,
            @RequestBody @Valid WorkoutToUpdateDTO dto
    ) {
        facade.updateWorkout(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteWorkout(
            @PathVariable Long id
    ) {
        facade.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/list-order")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> update(
            @RequestBody @Valid List<WorkoutToUpdateListOrderDTO> dto
            ) {
        facade.updateWorkoutsListOrder(dto);
        return ResponseEntity.ok().build();
    }
}
