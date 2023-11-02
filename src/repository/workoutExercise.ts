import prisma from "../config/prisma";
import { CreateWorkoutExerciseType } from "../schemas/workoutExercise";

export const create = async (data: CreateWorkoutExerciseType) =>
    await prisma.workoutExercise.create({ data });

export const deleteAllByWorkoutId = async (workout_id: string) =>
    await prisma.workoutExercise.deleteMany({ where: { workout_id } });
