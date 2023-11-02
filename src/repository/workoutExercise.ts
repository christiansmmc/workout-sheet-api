import prisma from "../config/prisma";
import { CreateWorkoutExerciseType } from "../schemas/workoutExercise";

export const create = async (data: CreateWorkoutExerciseType) =>
    await prisma.workoutExercise.create({ data });

export const deleteAllByWorkoutId = async (workout_id: string) =>
    await prisma.workoutExercise.deleteMany({ where: { workout_id } });

export const findByWorkoutAndExercise = async (
    workout_id: string,
    exercise_id: string,
    client_id: string
) => {
    const workoutExercise = await prisma.workoutExercise.findUnique({
        where: {
            workout_id_exercise_id: { workout_id, exercise_id },
            workout: {
                client_id,
            },
        },
    });

    if (!workoutExercise) {
        throw new Error("workout exercise not found!");
    }

    return workoutExercise;
};

export const deleteById = async (id: string) =>
    await prisma.workoutExercise.delete({ where: { id } });

export const deleteByWorkoutAndExercise = async (
    workout_id: string,
    exercise_id: string,
    client_id: string
) =>
    await prisma.workoutExercise.delete({
        where: {
            workout_id_exercise_id: { workout_id, exercise_id },
            workout: {
                client_id,
            },
        },
    });

export const updateLoad = async (
    workout_id: string,
    exercise_id: string,
    client_id: string,
    load: any
) =>
    await prisma.workoutExercise.update({
        where: {
            workout_id_exercise_id: { workout_id, exercise_id },
            workout: {
                client_id,
            },
        },
        data: { load: load },
    });
