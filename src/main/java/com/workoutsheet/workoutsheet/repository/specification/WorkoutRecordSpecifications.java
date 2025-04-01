package com.workoutsheet.workoutsheet.repository.specification;

import com.workoutsheet.workoutsheet.domain.WorkoutRecord;
import com.workoutsheet.workoutsheet.facade.enumeration.DateFilterPeriod;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class WorkoutRecordSpecifications {

    public static Specification<WorkoutRecord> withClient(Long clientId) {
        return (root, query, builder) ->
                builder.equal(root.get("workout").get("client").get("id"), clientId);
    }

    public static Specification<WorkoutRecord> withWorkoutId(Long workoutId) {
        return (root, query, builder) ->
                workoutId == null ? null : builder.equal(root.get("workout").get("id"), workoutId);
    }

    public static Specification<WorkoutRecord> withPeriod(DateFilterPeriod period) {
        return (root, query, builder) -> {
            if (period == null || period == DateFilterPeriod.ALL) return null;

            LocalDate now = LocalDate.now();
            LocalDate init;

            switch (period) {
                case CURRENT_MONTH -> init = now.withDayOfMonth(1);
                case LAST_3_MONTHS -> init = now.minusMonths(3).withDayOfMonth(1);
                default -> init = null;
            }

            return init == null
                    ? null
                    : builder.greaterThanOrEqualTo(root.get("date"), init);
        };
    }
}
