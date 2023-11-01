import { Static, Type } from "@sinclair/typebox";

export const CreateWorkoutExerciseSchema = Type.Object({
    load: Type.Any(),
    exercise_id: Type.String(),
    workout_id: Type.String(),
});

export type CreateWorkoutExerciseType = Static<
    typeof CreateWorkoutExerciseSchema
>;
