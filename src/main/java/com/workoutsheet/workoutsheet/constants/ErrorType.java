package com.workoutsheet.workoutsheet.constants;

import lombok.Getter;

@Getter
public enum ErrorType {
    WORKOUT_EXERCISE_NOT_FOUND("001", "Exercício não encontrado no treino."),
    CLIENT_DONT_HAVE_ACCESS("002", "Você não tem permissão para acessar essa informação."),
    WORKOUT_NOT_FOUND("003", "Treino não encontrado."),
    INVALID_CREDENTIALS("004", "Credenciais inválidas. Verifique seu e-mail e senha."),
    LOGGED_USER_NOT_FOUND("005", "Sessão expirada ou inválida. Faça login para continuar."),
    CLIENT_NOT_FOUND("006", "Nenhum cliente encontrado com o e-mail informado."),
    EXERCISE_NOT_FOUND("007", "Exercício não encontrado."),
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
