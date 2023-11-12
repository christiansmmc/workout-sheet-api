import { BodyPart } from "@prisma/client";
import prisma from "../config/prisma";

export const findAll = async (bodyPart: BodyPart) => {
    if (bodyPart && Object.values(BodyPart).includes(bodyPart as BodyPart)) {
        return prisma.exercise.findMany({
            where: {bodyPart},
        });
    }
    return prisma.exercise.findMany();
};

export const findById = async (id: string) => {
    const exercise = await prisma.exercise.findUnique({
        where: { id }
    });

    if (!exercise) {
        throw new Error("exercise not found");
    }

    return exercise;
};