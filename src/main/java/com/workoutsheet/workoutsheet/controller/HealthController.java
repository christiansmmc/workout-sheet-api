package com.workoutsheet.workoutsheet.controller;

import com.workoutsheet.workoutsheet.facade.vm.workout.HealthResponseVM;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
public class HealthController {

    @PatchMapping("/")
    public ResponseEntity<HealthResponseVM> healthChekc() {
        return ResponseEntity.ok(HealthResponseVM.builder().build());
    }
}
