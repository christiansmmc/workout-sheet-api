import { Static, Type } from "@sinclair/typebox";

export const CreateClientExerciseHistorySchema = Type.Object({
    load: Type.Any(),
    client_id: Type.String(),
    exercise_id: Type.String(),
});

export type CreateClientExerciseHistoryType = Static<
    typeof CreateClientExerciseHistorySchema
>;
