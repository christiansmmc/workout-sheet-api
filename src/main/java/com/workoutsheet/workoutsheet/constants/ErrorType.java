package com.workoutsheet.workoutsheet.constants;

import lombok.Getter;

@Getter
public enum ErrorType {
    WORKOUT_EXERCISE_NOT_FOUND("001", "Exercício não encontrado no treino"),
    CLIENT_DONT_HAVE_ACCESS("002", "Cliente não tem acesso a essa informação"),
    ;

    private final String code;
    private final String message;

    ErrorType(
            String code,
            String message
    ) {
        this.code = code;
        this.message = message;
    }
}
