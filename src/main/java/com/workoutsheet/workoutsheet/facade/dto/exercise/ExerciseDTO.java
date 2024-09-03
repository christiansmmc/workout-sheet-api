package com.workoutsheet.workoutsheet.facade.dto.exercise;

import com.workoutsheet.workoutsheet.domain.enumeration.BodyPart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO {

    private Long id;

    private String name;

    private BodyPart bodyPart;
}
