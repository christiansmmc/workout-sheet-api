import { Static, Type } from "@sinclair/typebox";

export const CreateWorkoutExerciseSchema = Type.Object({
    load: Type.Any(),
    exerciseId: Type.String(),
    workoutId: Type.String(),
});

export type CreateWorkoutExerciseType = Static<
    typeof CreateWorkoutExerciseSchema
>;
