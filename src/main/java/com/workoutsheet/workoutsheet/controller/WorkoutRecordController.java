package com.workoutsheet.workoutsheet.controller;

import com.workoutsheet.workoutsheet.facade.WorkoutRecordFacade;
import com.workoutsheet.workoutsheet.facade.dto.WorkoutRecordToFindAllSimpleDTO;
import com.workoutsheet.workoutsheet.facade.vm.FindWorkoutRecordFilterParamsVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.create.WorkoutRecordToCreateVM;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.find.WorkoutRecordToFindWorkoutRecordVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workout-record")
@RequiredArgsConstructor
public class WorkoutRecordController {

    private final WorkoutRecordFacade facade;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<WorkoutRecordToFindWorkoutRecordVM> create(
            @RequestBody @Valid WorkoutRecordToCreateVM vm
    ) throws URISyntaxException {
        WorkoutRecordToFindWorkoutRecordVM response = facade.create(vm);
        return ResponseEntity
                .created(new URI("/api/workout-record"))
                .body(response);
    }

    @GetMapping("/simple")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<WorkoutRecordToFindAllSimpleDTO>> findAllSimpleFiltered(
            @ModelAttribute @ParameterObject FindWorkoutRecordFilterParamsVM filterParams
    ) {
        List<WorkoutRecordToFindAllSimpleDTO> response = facade.findAllSimpleFiltered(filterParams);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/last")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<WorkoutRecordToFindWorkoutRecordVM> findLastFromWorkout(
            @RequestParam Long workoutId
    ) {
        Optional<WorkoutRecordToFindWorkoutRecordVM> response = facade.findLastFromWorkout(workoutId);
        return ResponseEntity.ok(response.orElse(null));
    }
}
