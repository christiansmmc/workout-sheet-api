package com.workoutsheet.workoutsheet.service.record;

import com.workoutsheet.workoutsheet.domain.Client;
import com.workoutsheet.workoutsheet.domain.Workout;
import com.workoutsheet.workoutsheet.domain.WorkoutRecord;
import com.workoutsheet.workoutsheet.facade.vm.FindWorkoutRecordFilterParamsVM;
import com.workoutsheet.workoutsheet.repository.record.WorkoutRecordRepository;
import com.workoutsheet.workoutsheet.repository.specification.WorkoutRecordSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutRecordService {

    private final WorkoutRecordRepository repository;

    public WorkoutRecord save(WorkoutRecord workoutRecord) {
        return repository.save(workoutRecord);
    }

    public List<WorkoutRecord> findAllWithFilter(
            FindWorkoutRecordFilterParamsVM filterParams,
            Client loggedClient
    ) {
        Specification<WorkoutRecord> spec = Specification.where(WorkoutRecordSpecifications.withClient(loggedClient.getId()))
                .and(WorkoutRecordSpecifications.withWorkoutId(filterParams.getWorkoutId()))
                .and(WorkoutRecordSpecifications.withPeriod(filterParams.getPeriod()));

        return repository.findAll(spec);
    }

    public Optional<WorkoutRecord> findLastFromWorkout(Workout workout, Client client) {
        return repository.findTopByWorkoutAndWorkout_ClientOrderByDateDesc(
                workout,
                client
        );
    }
}
