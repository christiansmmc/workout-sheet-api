import prisma from "../config/prisma";
import { CreateWorkoutType } from "../schemas/workout";

export const create = async (data: CreateWorkoutType) =>
    await prisma.workout.create({ data });

export const findAllByClientId = async (client_id: string) =>
    await prisma.workout.findMany({
        where: {
            client_id,
        },
    });

export const findById = async (id: string) => {
    const workout = await prisma.workout.findUnique({
        where: {
            id,
        },
        include: {
            workoutExercise: {
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
