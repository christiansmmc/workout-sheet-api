package com.workoutsheet.workoutsheet.constants;

import lombok.Getter;

@Getter
public enum ErrorType {
    WORKOUT_EXERCISE_NOT_FOUND("001", "Exercício não encontrado no treino"),
    CLIENT_DONT_HAVE_ACCESS("002", "Cliente não tem acesso a essa informação"),
    WORKOUT_NOT_FOUND("003", "Treino não encontrado"),
    INVALID_CREDENTIALS("004", "Credenciais inválidas. Verifique seu email e senha."),
    LOGGED_USER_NOT_FOUND("005", "Por favor, faça login para continuar."),
    CLIENT_NOT_FOUND("006", "Cliente não encontrado com email fornecido"),
    EXERCISE_NOT_FOUND("007", "Exercício não encontrado"),
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
