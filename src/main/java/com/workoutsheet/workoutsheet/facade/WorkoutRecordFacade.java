package com.workoutsheet.workoutsheet.facade;

import com.workoutsheet.workoutsheet.context.WorkoutRecordContext;
import com.workoutsheet.workoutsheet.facade.vm.workoutrecord.create.WorkoutRecordToCreateVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class WorkoutRecordFacade {

    private final WorkoutRecordContext context;

    @Transactional
    public void create(WorkoutRecordToCreateVM vm) {
        context.create(vm);
    }
}
