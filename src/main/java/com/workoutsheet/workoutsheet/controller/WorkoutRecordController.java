package com.workoutsheet.workoutsheet.controller;

import com.workoutsheet.workoutsheet.facade.WorkoutRecordFacade;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.create.WorkoutRecordToCreateVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/workout-record")
@RequiredArgsConstructor
public class WorkoutRecordController {

    private final WorkoutRecordFacade facade;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> create(
            @RequestBody @Valid WorkoutRecordToCreateVM vm
    ) throws URISyntaxException {
        facade.create(vm);
        return ResponseEntity
                .created(new URI("/api/workout-record"))
                .build();
    }
}
