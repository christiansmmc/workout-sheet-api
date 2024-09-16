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

@Table(name = "workout_exercise")
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer sets;

    private Integer reps;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Builder.Default
    private BigDecimal exerciseLoad = BigDecimal.ZERO;

    @ManyToOne
    private Workout workout;

    @ManyToOne
    private Exercise exercise;
}
