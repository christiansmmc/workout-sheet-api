import prisma from "../config/prisma";
import { CreateClientExerciseHistoryType } from "../schemas/clientExerciseRecordSchema";

export const create = async (data: CreateClientExerciseHistoryType) =>
    await prisma.clientExerciseRecord.create({ data });
