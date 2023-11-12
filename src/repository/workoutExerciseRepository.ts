import prisma from "../config/prisma";
import {CreateWorkoutExerciseType} from "../schemas/workoutExerciseSchema";

export const create = async (data: CreateWorkoutExerciseType) => {
    console.log(data)
    console.log("AQUI EM CIMA")
    await prisma.workoutExercise.create({data});
}

export const deleteAllByWorkoutId = async (workoutId: string) =>
    await prisma.workoutExercise.deleteMany({where: {workoutId}});

export const findByWorkoutAndExercise = async (
    workoutId: string,
    exerciseId: string,
    clientId: string
) => {
    const workoutExercise = await prisma.workoutExercise.findUnique({
        where: {
            workoutId_exerciseId: {workoutId: workoutId, exerciseId},
            workout: {
                clientId,
            },
        },
    });

    if (!workoutExercise) {
        throw new Error("workout exercise not found!");
    }

    return workoutExercise;
};

export const deleteById = async (id: string) =>
    await prisma.workoutExercise.delete({where: {id}});

export const deleteByWorkoutAndExercise = async (
    workoutId: string,
    exerciseId: string,
    clientId: string
) =>
    await prisma.workoutExercise.delete({
        where: {
            workoutId_exerciseId: {workoutId, exerciseId},
            workout: {
                clientId,
            },
        },
    });

export const updateLoad = async (
    workoutId: string,
    exerciseId: string,
    clientId: string,
    load: any
) =>
    await prisma.workoutExercise.update({
        where: {
            workoutId_exerciseId: {workoutId, exerciseId},
            workout: {
                clientId,
            },
        },
        data: {load},
    });
