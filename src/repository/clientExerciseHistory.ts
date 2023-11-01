import prisma from "../config/prisma";
import { CreateClientExerciseHistoryType } from "../schemas/clientExerciseHistory";

export const create = async (data: CreateClientExerciseHistoryType) =>
    await prisma.clientExerciseHistory.create({ data });
