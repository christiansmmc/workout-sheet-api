import { BodyPart } from "@prisma/client";
import { Static, Type } from "@sinclair/typebox";

export const WorkoutIdSchema = Type.Object({
    id: Type.String(),
});

export const CreateWorkoutSchema = Type.Object({
    name: Type.String(),
    client_id: Type.String(),
});

export const CreateCompleteWorkoutSchema = Type.Object({
    name: Type.String(),
    workoutExercises: Type.Array(
        Type.Object({
            load: Type.Any(),
            exercise_id: Type.String(),
        })
    ),
});

export const FindAllWorkoutSchema = Type.Array(
    Type.Object({
        id: Type.String(),
        name: Type.String(),
    })
);

export const FindWorkoutByIdSchema = Type.Object({
    id: Type.String(),
    name: Type.String(),
    workoutExercise: Type.Array(
        Type.Object({
            id: Type.String(),
            load: Type.Any(),
            exercise: Type.Object({
                id: Type.String(),
                name: Type.String(),
                bodyPart: Type.Enum(BodyPart),
            }),
        })
    ),
});

export const CreateUpdateWorkoutLoadSchema = Type.Object({
    load: Type.Any(),
});

export type CreateWorkoutType = Static<typeof CreateWorkoutSchema>;
export type CreateCompleteWorkoutType = Static<
    typeof CreateCompleteWorkoutSchema
>;
export type FindAllWorkoutType = Static<typeof FindAllWorkoutSchema>;
export type FindWorkoutByIdType = Static<typeof FindWorkoutByIdSchema>;
export type CreateUpdateWorkoutLoadType = Static<
    typeof CreateUpdateWorkoutLoadSchema
>;
