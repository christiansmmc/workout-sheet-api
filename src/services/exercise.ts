import { BodyPart } from "@prisma/client";
import { findAll } from "../repository/exercise";

export const findAllExercises = async (bodyPart: BodyPart) => {
    return await findAll(bodyPart);
};
