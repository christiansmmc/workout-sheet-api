import { Static, Type } from "@sinclair/typebox";

export const CreateClientExerciseRecordSchema = Type.Object({
    load: Type.Any(),
    clientId: Type.String(),
    exerciseId: Type.String(),
});

export type CreateClientExerciseHistoryType = Static<
    typeof CreateClientExerciseRecordSchema
>;
