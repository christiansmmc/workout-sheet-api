import prisma from "../config/prisma";
import { CreateWorkoutType } from "../schemas/workoutSchema";

export const create = async (data: CreateWorkoutType) =>
    await prisma.workout.create({ data });

export const findAllByClientId = async (clientId: string) =>
    await prisma.workout.findMany({
        where: { clientId },
    });

export const findById = async (id: string) => {
    const workout = await prisma.workout.findUnique({
        where: {
            id,
        },
        include: {
            workoutExercises: {
                include: { exercise: true },
            },
        },
    });

    if (!workout) {
        throw new Error("Workout not found");
    }

    return workout;
};

export const deleteById = async (id: string) =>
    await prisma.workout.delete({ where: { id } });
