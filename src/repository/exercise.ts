import { BodyPart } from "@prisma/client";
import prisma from "../config/prisma";

export const findAll = async (bodyPart: BodyPart) => {
    if (bodyPart && Object.values(BodyPart).includes(bodyPart as BodyPart)) {
        return await prisma.exercise.findMany({
            where: { bodyPart: bodyPart },
        });
    }
    return await prisma.exercise.findMany();
};
