package com.workoutsheet.workoutsheet.domain;

import com.workoutsheet.workoutsheet.domain.enumeration.WorkoutRecordExerciseStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "workout_record_exercise")
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutRecordExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String note;

    @NotNull
    @Enumerated(EnumType.STRING)
    private WorkoutRecordExerciseStatus status;

    @NotNull
    @ManyToOne
    private Exercise exercise;

    @NotNull
    @ManyToOne
    private WorkoutRecord workoutRecord;
}
