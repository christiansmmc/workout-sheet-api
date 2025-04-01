package com.workoutsheet.workoutsheet.service.record;

import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.Exercise;
import com.workoutsheet.workoutsheet.domain.ExerciseLoadRecord;
import com.workoutsheet.workoutsheet.repository.record.ExerciseLoadRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExerciseLoadRecordService {

    private final ExerciseLoadRecordRepository repository;

    public void create(
            Exercise exercise,
            Client client,
            BigDecimal load,
            LocalDate date
    ) {
        ExerciseLoadRecord exerciseLoadRecord = ExerciseLoadRecord
                .builder()
                .exerciseLoad(load)
                .date(date)
                .client(client)
                .exercise(exercise)
                .build();

        repository.save(exerciseLoadRecord);
    }
}
