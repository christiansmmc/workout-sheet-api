import {BodyPart} from "@prisma/client";
import prisma from "../config/prisma";

export const findAll = async (bodyParts?: BodyPart[]) => {
    if (bodyParts && bodyParts.length > 0) {
        return prisma.exercise.findMany({
            where: {
                bodyPart: {
                    in: bodyParts,
                },
            },
        });
    }
    return prisma.exercise.findMany();
};

export const findById = async (id: string) => {
    const exercise = await prisma.exercise.findUnique({
        where: {id}
    });

    if (!exercise) {
        throw new Error("exercise not found");
    }

    return exercise;
};