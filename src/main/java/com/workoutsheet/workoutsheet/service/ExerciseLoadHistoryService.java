package com.workoutsheet.workoutsheet.service;

import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.Exercise;
import com.workoutsheet.workoutsheet.domain.ExerciseLoadHistory;
import com.workoutsheet.workoutsheet.repository.ExerciseLoadHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExerciseLoadHistoryService {

    private final ExerciseLoadHistoryRepository repository;

    public void create(
            Exercise exercise,
            Client client,
            BigDecimal load,
            LocalDate date
    ) {
        ExerciseLoadHistory exerciseLoadHistory = ExerciseLoadHistory
                .builder()
                .exerciseLoad(load)
                .date(date)
                .client(client)
                .exercise(exercise)
                .build();

        repository.save(exerciseLoadHistory);
    }
}
