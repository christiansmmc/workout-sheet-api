package com.workoutsheet.workoutsheet.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Table(name = "workout_history_exercise_set")
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutHistoryExerciseSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer set;

    @NotNull
    private Integer reps;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal exerciseLoad;

    private String note;

    @NotNull
    @ManyToOne
    private WorkoutHistoryExercise workoutHistoryExercise;
}
