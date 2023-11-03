import { BodyPart } from "@prisma/client";
import { Static, Type } from "@sinclair/typebox";

export const ExerciseSchema = Type.Object({
    id: Type.String(),
    name: Type.String(),
    bodyPart: Type.Enum(BodyPart),
});

export type ExerciseType = Static<typeof ExerciseSchema>;
